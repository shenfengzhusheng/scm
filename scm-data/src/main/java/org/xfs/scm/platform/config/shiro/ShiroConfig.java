package org.xfs.scm.platform.config.shiro;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.xfs.scm.data.system.user.service.UserServiceI;
import org.xfs.scm.platform.config.redis.service.CacheServiceI;
import org.xfs.scm.platform.config.shiro.credentials.RetryLimitHashedCredentialsMatcher;
import org.xfs.scm.platform.config.shiro.custom.QuartzSessionValidationScheduler2;
import org.xfs.scm.platform.config.shiro.filter.CustomFormAuthenticationFilter;
import org.xfs.scm.platform.config.shiro.filter.FormLoginFilter;
import org.xfs.scm.platform.config.shiro.listener.CustomSessionListener;
import org.xfs.scm.platform.config.shiro.realm.UserRealm;
import org.xfs.scm.platform.config.shiro.redis.RedisCacheManager;
import org.xfs.scm.platform.config.shiro.redis.RedisSessionDao;

import javax.servlet.Filter;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class ShiroConfig {

    private static final Logger logger= LoggerFactory.getLogger(ShiroConfig.class);


    /**
     * 	/static/** = anon
     /resources/** = anon
     /logout = logout
     /authenticated = anon
     /login = anon
     /main* = authc
     * @return
     */

    @Bean(name = "cacheManager")
    public RedisCacheManager cacheManager(CacheServiceI cacheService){
        RedisCacheManager redisCacheManager=new RedisCacheManager(cacheService,"scm-data_shiro_redis_cache_manager:");
        return redisCacheManager;
    }

    @Bean(name = "redisSessionDao")
    public RedisSessionDao redisSessionDao(CacheServiceI cacheService){
        RedisSessionDao redisSessionDao=new RedisSessionDao(cacheService,"scm-data_shiro_redis_session_manager:");
        redisSessionDao.setExpire(30*60);
        return redisSessionDao;
    }

    @Bean(name = "sessionManager")
    public DefaultWebSessionManager sessionManager(SimpleCookie sessionIdCookie,RedisSessionDao redisSessionDao){
        DefaultWebSessionManager sessionManager=new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDao);
        sessionManager.setGlobalSessionTimeout(60*30*1000);//30分钟session
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        Collection<SessionListener> sessionListeners=sessionManager.getSessionListeners();
        sessionListeners.add(new CustomSessionListener());
        sessionManager.setSessionListeners(sessionListeners);
        sessionManager.setSessionValidationScheduler(sessionValidationScheduler(sessionManager));
        //securityManager().setCacheManager(redisCacheManager());
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setSessionIdCookie(sessionIdCookie);
        return sessionManager;
    }

    @Bean
    public CustomSessionListener customSessionListener(RedisSessionDao redisSessionDao){
        CustomSessionListener customSessionListener=new CustomSessionListener();
        customSessionListener.setRedisSessionDao(redisSessionDao);
        return customSessionListener;
    }

    @Bean(name = "credentialsMatcher")
    public RetryLimitHashedCredentialsMatcher credentialsMatcher(RedisCacheManager cacheManager){
        RetryLimitHashedCredentialsMatcher credentialsMatcher=new RetryLimitHashedCredentialsMatcher(cacheManager);
        return credentialsMatcher;
    }
    @Bean(name = "userRealm")
    public UserRealm userRealm(UserServiceI userService,RedisCacheManager cacheManager,RetryLimitHashedCredentialsMatcher credentialsMatcher){
        if(logger.isDebugEnabled()){
            logger.debug("-----------------init userRealm--------------->{}",userService);
        }
        UserRealm userRealm=new UserRealm();
        userRealm.setCredentialsMatcher(credentialsMatcher);
        userRealm.setCacheManager(cacheManager);
        userRealm.setCachingEnabled(true);
        userRealm.setUserService(userService);
        userRealm.setAuthenticationCachingEnabled(true);
        userRealm.setAuthenticationCacheName("authenticationCache");
        userRealm.setAuthorizationCachingEnabled(true);
        userRealm.setAuthorizationCacheName("authorizationCache");
        return userRealm;
    }


    @Bean(name="sessionIdGenerator")
    public JavaUuidSessionIdGenerator sessionIdGenerator(){
        JavaUuidSessionIdGenerator sessionIdGenerator=new JavaUuidSessionIdGenerator();
        return sessionIdGenerator;
    }



    @Bean(name = "sessionIdCookie")
    public SimpleCookie sessionIdCookie(){
        SimpleCookie sessionIdCookie=new SimpleCookie("sid");
        sessionIdCookie.setHttpOnly(true);
        sessionIdCookie.setMaxAge(-1);
        //   sessionIdCookie.setPath("/");
        return sessionIdCookie;
    }

    @Bean(name = "rememberMeCookie")
    public SimpleCookie rememberMeCookie(){
        SimpleCookie rememberMeCookie=new SimpleCookie("rememberMe");
        rememberMeCookie.setMaxAge(2592000);
        rememberMeCookie.setName("manager");
        return rememberMeCookie;
    }


    @Bean(name = "rememberMeManager")
    public CookieRememberMeManager rememberMeManager(SimpleCookie rememberMeCookie){
        CookieRememberMeManager rememberMeManager=new CookieRememberMeManager();
        rememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        rememberMeManager.setCookie(rememberMeCookie);
        return rememberMeManager;
    }


    @Bean(name = "sessionValidationScheduler")
    public QuartzSessionValidationScheduler2  sessionValidationScheduler(DefaultWebSessionManager sessionManager){
        QuartzSessionValidationScheduler2 sessionValidationScheduler=new QuartzSessionValidationScheduler2();
        sessionValidationScheduler.setSessionValidationInterval(1800000);

        sessionValidationScheduler.setSessionManager(sessionManager);
        return sessionValidationScheduler;
    }
    ///-----------------------------checked---------------------------------------------


   @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager(DefaultWebSessionManager sessionManager,RedisCacheManager cacheManager,UserRealm userRealm,CookieRememberMeManager rememberMeManager){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        securityManager.setSessionManager(sessionManager);
        securityManager.setCacheManager(cacheManager);
        securityManager.setRememberMeManager(rememberMeManager);
        return securityManager;
    }

    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean(DefaultWebSecurityManager securityManager){
        MethodInvokingFactoryBean methodInvokingFactoryBean=new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        methodInvokingFactoryBean.setArguments(new Object[]{securityManager});
        return methodInvokingFactoryBean;
    }

    @Bean(name="customFormAuthenticationFilter")
    public FormAuthenticationFilter formAuthenticationFilter(){
        FormAuthenticationFilter formAuthenticationFilter=new FormAuthenticationFilter();
        formAuthenticationFilter.setUsernameParam("username");
        formAuthenticationFilter.setPasswordParam("password");
        formAuthenticationFilter.setRememberMeParam("rememberMe");
        formAuthenticationFilter.setLoginUrl("/login");
        formAuthenticationFilter.setSuccessUrl("/main");
        formAuthenticationFilter.setFailureKeyAttribute("shiroLoginFailure");
        return formAuthenticationFilter;
    }

    @Bean(name = "logout")
    public LogoutFilter logoutFilter(){
        LogoutFilter logoutFilter=new LogoutFilter();
        logoutFilter.setRedirectUrl("/login");
        return logoutFilter;
    }
    @SuppressWarnings("rawtypes")
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSessionManager sessionManager,RedisCacheManager cacheManager,UserRealm userRealm,CookieRememberMeManager rememberMeManager, UserServiceI userService){
        InputStream inputStream=ShiroConfig.class.getResourceAsStream("/config/properties/dev/shiro.properties");

        Properties prop=new Properties();
        try {
            ShiroFilterFactoryBean shiroFilter=new ShiroFilterFactoryBean();

            shiroFilter.setLoginUrl("/login");
            shiroFilter.setSuccessUrl("/main");
            shiroFilter.setUnauthorizedUrl("/refuse");
            Map<String, String> filterChain = new LinkedHashMap<>();
            prop.load(inputStream);
            Set set = prop.entrySet();
            for (Object entry : set) {
                Map.Entry map = (Map.Entry) entry;
                if(logger.isDebugEnabled()){
                    logger.debug("{}={}", map.getKey(),map.getValue());
                }
             //   System.out.printf("%s = %s%n",map.getKey(),map.getValue() );
                filterChain.put( map.getKey().toString(),  map.getValue().toString());
            }
            shiroFilter.setFilterChainDefinitionMap(filterChain);
            shiroFilter.setSecurityManager(securityManager(sessionManager,cacheManager,userRealm,rememberMeManager));
            Map<String, Filter>filterMap=new HashMap<>();
           // filterMap.put("authc",customFormAuthenticationFilter());
            filterMap.put("authc",formAuthenticationFilter());
            shiroFilter.setFilters(filterMap);

            return shiroFilter;
        } catch (IOException e) {
            logger.error("shiro start error!");
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }
}
