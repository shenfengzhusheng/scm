package org.xfs.scm.platform.config.mq.active;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.util.backoff.FixedBackOff;
import org.xfs.scm.data.business.pay.record.service.PayRecordServiceI;
import org.xfs.scm.data.system.payaccount.service.PayAccountServiceI;
import org.xfs.scm.platform.config.listener.ActiveMessageListener;
import org.xfs.scm.platform.config.redis.service.CacheServiceI;

import javax.jms.Destination;
import javax.jms.Message;

public class ActiveMqConsumer {
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
    private String defaultQueueName;

    @Bean(name = "targetConnectionFactory")
    public ActiveMQConnectionFactory targetConnectionFactory(){
        ActiveMQConnectionFactory targetConnectionFactory=new ActiveMQConnectionFactory();
        targetConnectionFactory.setBrokerURL(this.borkerURL);
        targetConnectionFactory.setUserName(this.userName);
        targetConnectionFactory.setPassword(this.password);
        return targetConnectionFactory;
    }

    @Bean(value = "pooledConnectionFactory",initMethod = "start",destroyMethod = "stop")
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
    //这个是目的地:mailQueue
    @Bean
    public Destination mailQueue() {
        ActiveMQQueue mailQueue=new ActiveMQQueue(this.defaultQueueName);
        return mailQueue;
    }

    @Bean(name = "messageListener")
    public SessionAwareMessageListener<Message> queueMessageListener(CacheServiceI cacheService,PayRecordServiceI payRecordService,PayAccountServiceI payAccountService) {
        return new ActiveMessageListener(payRecordService,payAccountService);
    }
    @Bean
    public MessageListenerContainer sessionAwareListenerContainer(SingleConnectionFactory connectionFactory, SessionAwareMessageListener<Message> messageListener, Destination mailQueue) {
        DefaultMessageListenerContainer sessionAwareListenerContainer=new DefaultMessageListenerContainer();
        sessionAwareListenerContainer.setConnectionFactory(connectionFactory);
        sessionAwareListenerContainer.setDestination(mailQueue);
        sessionAwareListenerContainer.setMessageListener(messageListener);
        FixedBackOff backOff=new FixedBackOff();
        backOff.setMaxAttempts(3);
        sessionAwareListenerContainer.setBackOff(backOff);
        sessionAwareListenerContainer.setConcurrency("4-12");
        sessionAwareListenerContainer.setPubSubNoLocal(false);
        return sessionAwareListenerContainer;
    }
}
