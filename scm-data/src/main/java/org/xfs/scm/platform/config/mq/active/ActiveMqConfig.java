package org.xfs.scm.platform.config.mq.active;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;
import org.xfs.scm.data.business.pay.record.service.PayRecordServiceI;
import org.xfs.scm.data.system.payaccount.service.PayAccountServiceI;
import org.xfs.scm.platform.config.mq.active.listener.ConsumerMessageListener;
import org.xfs.scm.platform.config.redis.service.CacheServiceI;

import javax.jms.Destination;

public class ActiveMqConfig {

    private static final Logger LOG= LoggerFactory.getLogger(ActiveMqConsumer.class);

    @Value("${mq.brokerURL}")
    private String borkerURL;

    @Value("${mq.userName}")
    private String userName;

    @Value("${mq.password}")
    private String password;

    @Value("${mq.maxConnections}")
    private int maxConnections;

    @Value("${mq.defaultQueueName}")
    private String queuqName;


    @Bean(name = "targetConnectionFactory")
    public ActiveMQConnectionFactory targetConnectionFactory(){
        ActiveMQConnectionFactory targetConnectionFactory=new ActiveMQConnectionFactory();
        targetConnectionFactory.setBrokerURL(this.borkerURL);
        targetConnectionFactory.setUserName(this.userName);
        targetConnectionFactory.setPassword(this.password);
        return targetConnectionFactory;
    }

    @Bean
    public PooledConnectionFactory pooledConnectionFactory(ActiveMQConnectionFactory targetConnectionFactory){
        PooledConnectionFactory pooledConnectionFactory=new PooledConnectionFactory(targetConnectionFactory);
        pooledConnectionFactory.setMaxConnections(this.maxConnections);
        pooledConnectionFactory.setMaximumActiveSessionPerConnection(this.maxConnections);
        return pooledConnectionFactory;
    }
//    @Bean(name = "connectionFactory")
//    public CachingConnectionFactory connectionFactory(ActiveMQConnectionFactory targetConnectionFactory){
//        CachingConnectionFactory cachingConnectionFactory=new CachingConnectionFactory();
//        cachingConnectionFactory.setTargetConnectionFactory(targetConnectionFactory);
//        cachingConnectionFactory.setSessionCacheSize(100);
//        return cachingConnectionFactory;
//    }
    @Bean
    public ConsumerMessageListener messageListener(CacheServiceI cacheService, PayRecordServiceI payRecordService, PayAccountServiceI payAccountService){
        return new ConsumerMessageListener(cacheService,payRecordService,payAccountService);
    }
    @Bean
    public Destination destination() {
        ActiveMQQueue queue=new ActiveMQQueue(this.queuqName);
        return queue;
    }

    @Bean
    public JmsTemplate jmsTemplate(ActiveMQConnectionFactory activeMQConnectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(activeMQConnectionFactory);
        //jmsTemplate.setConnectionFactory(connectionFactory);
        jmsTemplate.setDefaultDestinationName(this.queuqName);
        return jmsTemplate;
    }

    @Bean
    public MessageListenerContainer jmsContainer(PooledConnectionFactory pooledConnectionFactory,Destination destination,ConsumerMessageListener consumerMessageListener){
        DefaultMessageListenerContainer defaultMessageListenerContainer=new DefaultMessageListenerContainer();
        defaultMessageListenerContainer.setConnectionFactory(pooledConnectionFactory);
        defaultMessageListenerContainer.setDestination(destination);
        defaultMessageListenerContainer.setMessageListener(consumerMessageListener);
        defaultMessageListenerContainer.setConcurrentConsumers(8);
        defaultMessageListenerContainer.setMaxConcurrentConsumers(18);
        return defaultMessageListenerContainer;
    }

//    @Bean
//    public ActiveMQTopic activeMQTopic(){
//        ActiveMQTopic topic=new ActiveMQTopic("0");
//
//        return topic;
//    }

}
