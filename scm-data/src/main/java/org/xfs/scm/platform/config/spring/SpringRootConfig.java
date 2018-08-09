package org.xfs.scm.platform.config.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.xfs.scm.platform.config.aop.AopConfig;

/**
 * service 配置
 * Created by 神风逐胜 on 2017/10/7 0007.20:26
 * version:1.0
 */

@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan(basePackages = {"org.xfs.scm.data.business.pay.record.mapper","org.xfs.scm.**.service","com.qht.**.api","org.xfs.**.api",
        "org.xfs.scm.platform.config.shiro.redis","org.xfs.scm.platform.util.components","org.xfs.scm.platform.util.wx"})
public class SpringRootConfig {

    /**
     * 引入property配置文件
     * @return
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyLoader() {
        PropertySourcesPlaceholderConfigurer property = new PropertySourcesPlaceholderConfigurer();
        property.setLocations(new ClassPathResource("/config/properties/dev/db.properties"),
                new ClassPathResource("/config/properties/dev/redis.properties"),
                new ClassPathResource("/config/properties/dev/config.properties"),
                new ClassPathResource("/config/properties/dev/dubbo.properties"),
                new ClassPathResource("/config/properties/dev/mq.properties"),
                new ClassPathResource("/config/properties/dev/fdfs_client.properties"),
                new ClassPathResource("/config/properties/dev/mongodb.properties"));
        return property;
    }

    /**
     * 注入spring aop
     * @return
     */
    @Bean
    public AopConfig aopConfig() {
        return new AopConfig();
    }
}
