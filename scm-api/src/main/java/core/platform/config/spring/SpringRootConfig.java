package core.platform.config.spring;

import core.platform.aop.AopConfig;
import core.platform.aop.CacheableAop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

// import org.springframework.context.annotation.Import;

/**
 * aop 生效需要拦截点生效，aop容器正常注 入
 * 
 * @author Jeken.Liu
 *
 */
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan(basePackages = {"core.**.service"}/*
                                                          * , excludeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value =
                                                          * { Controller.class,Aspect.class }) }
                                                          */)
public class SpringRootConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyLoader() {
    	PropertySourcesPlaceholderConfigurer ppc = new PropertySourcesPlaceholderConfigurer();
        //ppc.setLocation(new ClassPathResource("application.properties"));
        ppc.setLocations(new ClassPathResource("/config/properties/dev/db.properties"),
        		new ClassPathResource("/config/properties/dev/redis.properties"));
        //ppc.setIgnoreUnresolvablePlaceholders(true);
        return ppc;
    }

    /**
     * aop bean
     * 
     * @return
     */
    @Bean
    public AopConfig aopConfig() {
        return new AopConfig();
    }

    /**
     * cache aop bean
     * 
     * @return
     */
    @Bean
    public CacheableAop cacheableAop() {
        return new CacheableAop();
    }
}
