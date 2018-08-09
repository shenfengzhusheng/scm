package org.xfs.scm.platform.config.dubbo;


import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
@PropertySource("classpath:/config/properties/dev/dubbo.properties")
@DubboComponentScan(basePackages = {"com.qht.**.api","org.xfs.**.api"})
public class DubboConsumerConfig {
    @Value("${dubbo.application.consumer.name}")
    private String applicationName;

    @Value("${dubbo.registr.protocol}")
    private String protocol;

    @Value("${dubbo.registry.address}")
    private String registryAddress;

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(this.applicationName);
        return applicationConfig;
    }
    /**
     * Random LoadBalance
     随机，按权重设置随机概率。
     在一个截面上碰撞的概率高，但调用量越大分布越均匀，而且按概率使用权重后也比较均匀，有利于动态调整提供者权重。
     RoundRobin LoadBalance
     轮循，按公约后的权重设置轮循比率。
     存在慢的提供者累积请求问题，比如：第二台机器很慢，但没挂，当请求调到第二台时就卡在那，久而久之，所有请求都卡在调到第二台上。
     LeastActive LoadBalance
     最少活跃调用数，相同活跃数的随机，活跃数指调用前后计数差。
     使慢的提供者收到更少请求，因为越慢的提供者的调用前后计数差会越大。
     ConsistentHash LoadBalance
     一致性Hash，相同参数的请求总是发到同一提供者。
     当某一台提供者挂时，原本发往该提供者的请求，基于虚拟节点，平摊到其它提供者，不会引起剧烈变动。
     算法参见：http://en.wikipedia.org/wiki/Consistent_hashing。
     缺省只对第一个参数Hash，如果要修改，请配置<dubbo:parameter key="hash.arguments" value="0,1" />
     缺省用160份虚拟节点，如果要修改，请配置<dubbo:parameter key="hash.nodes" value="320" />
     * @return
     */
    @Bean
    public ConsumerConfig consumerConfig() {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setCheck(false);
        consumerConfig.setTimeout(3000);
        consumerConfig.setLazy(true);
        consumerConfig.setLoadbalance("roundrobin");
        return consumerConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("zookeeper://"+registryAddress);
        //registryConfig.setPassword("4567889");
        registryConfig.setClient("curator");
        return registryConfig;
    }
}
