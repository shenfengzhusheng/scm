package org.xfs.scm.platform.config.shiro.credentials;

import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.xfs.scm.common.utils.encrypt.PasswordHelper;


public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher{
    // 声明一个缓存接口，这个接口是Shiro缓存管理的一部分，它的具体实现可以通过外部容器注入
    private Cache<String,Integer> passwordRetryCache;

    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info){
        String username= token.getPrincipal().toString();
        Integer retryCount=null;
        retryCount=passwordRetryCache.get(username+"_retry");
        if(retryCount==null){
            retryCount=0;
        }
        retryCount++;
        passwordRetryCache.put(username+"_retry",retryCount);

        // 自定义一个验证过程：当用户连续输入密码错误5次以上禁止用户登录一段时间
        if((retryCount)>5){
            throw new ExcessiveAttemptsException();
        }

        boolean match=this.match(token,info);
        if(match){
            passwordRetryCache.remove(username+"_retry");
        }
        return match;
    }

    private boolean match(AuthenticationToken token, AuthenticationInfo info){
         char[] token_passwd = ((UsernamePasswordToken) token).getPassword();//用户输入的密码

        String info_passwd = (String) info.getCredentials(); //数据库获取的密码
        byte[] info_salt = ((SimpleAuthenticationInfo) info).getCredentialsSalt().getBytes(); //数据库获取的盐

        String password=new String(token_passwd);
        return PasswordHelper.validPasswd(password, info_salt, info_passwd);
    }

}
