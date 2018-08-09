package org.xfs.mq.spring.config;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

public class ActiveMqConfig {
    private static final Logger LOG= LoggerFactory.getLogger(ActiveMqConfig.class);
    @Value("${mq.brokerURL}")
    private String borkerURL;
    @Value("${mq.userName}")
    private String userName;
    @Value("${mq.password}")
    private String password;
    @Value("${mq.maxConnections}")
    private int maxConnections;
    @Value("${mq.defaultQueueName}")
    private String defaultQueueName="netty";

    @Bean(name = "targetConnectionFactory")
    public ActiveMQConnectionFactory targetConnectionFactory(){
        ActiveMQConnectionFactory targetConnectionFactory=new ActiveMQConnectionFactory();
        targetConnectionFactory.setBrokerURL(this.borkerURL);
        targetConnectionFactory.setUserName(this.userName);
        targetConnectionFactory.setPassword(this.password);
        return targetConnectionFactory;
    }

    @Bean(value = "pooledConnectionFactory")
    public PooledConnectionFactory pooledConnectionFactory(ActiveMQConnectionFactory targetConnectionFactory){
        PooledConnectionFactory pooledConnectionFactory=new PooledConnectionFactory();
        pooledConnectionFactory.setConnectionFactory(targetConnectionFactory);
        pooledConnectionFactory.setMaxConnections(this.maxConnections);
        return pooledConnectionFactory;
    }

    @Bean(value = "connectionFactory")
    public SingleConnectionFactory connectionFactory(PooledConnectionFactory pooledConnectionFactory){
        SingleConnectionFactory singleConnectionFactory=new SingleConnectionFactory();
        singleConnectionFactory.setTargetConnectionFactory(pooledConnectionFactory);
        return singleConnectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate(SingleConnectionFactory connectionFactory){
        JmsTemplate jmsTemplate=new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory);
        jmsTemplate.setDefaultDestinationName(this.defaultQueueName);
        return jmsTemplate;
    }
}
