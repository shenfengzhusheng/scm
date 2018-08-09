package org.xfs.scm.platform.config.dubbo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;

@DubboComponentScan(basePackages = {"com.qht.**.api","org.xfs.**.api"})
public class DubboProviderConfig {
    private static final Logger logger=LoggerFactory.getLogger(DubboProviderConfig.class);

    @Value("${dubbo.application.name}")
    private String applicationName;

    @Value("${dubbo.registry.address}")
    private String registryAddress;

    @Value("${dubbo.protocol.name}")
    private String protocolName;

    @Value("${dubbo.protocol.port:20880}")
    private int protocolPort;

    @Value("${dubbo.provider.timeout:3000}")
    private int timeout;

    @Value("${dubbo.provider.registry.timeout:3000}")
    private int registry_timeout;

    @Value("${dubbo.provider.retries:1}")
    private int retries;

    @Value("${dubbo.provider.delay:1}")
    private int delay;

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(applicationName);
        return applicationConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("zookeeper://"+registryAddress);
        registryConfig.setTimeout(registry_timeout);

        // registryConfig.setPort(20891);
        registryConfig.setClient("curator");
        return registryConfig;
    }

    /**
     * 默认基于dubbo协议提供服务
     *
     * @return
     */
    @Bean
    public ProtocolConfig protocolConfig() {
        // 服务提供者协议配置
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName(this.protocolName);
        protocolConfig.setPort(protocolPort);
        protocolConfig.setThreads(200);
        if(logger.isDebugEnabled()) {
            logger.debug(("########### protocol config【"+this.protocolName+":"+protocolPort+"】"));
        }
        return protocolConfig;
    }
}
