package org.xfs.core.platform.config.mq.config;

import java.util.Arrays;

import org.apache.activemq.pool.PooledConnectionFactory;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.SimpleMessageListenerContainer;

@Configuration
public class MqConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(MqConfiguration.class);
    public static final String DEFAULT_BROKER_URL = "tcp://127.0.0.1:61616";

    public static final String ORDER_QUEUE = "order-queue";


    // @Bean
    public ActiveMQConnectionFactory mqConnectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(DEFAULT_BROKER_URL);
        connectionFactory.setTrustedPackages(Arrays.asList("org.xfs.core"));
        // connectionFactory.setUserName("admin");
        // connectionFactory.setPassword("admin");

        // connectionFactory.setc
        return connectionFactory;
    }

    //
    @Bean
    public PooledConnectionFactory mqConnectionPool() {
        if (logger.isInfoEnabled()) {
            logger.info("----------------init PooledConnectionFactory ---------------------");
        }
        PooledConnectionFactory pool = null;
        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
            connectionFactory.setBrokerURL(DEFAULT_BROKER_URL);
            connectionFactory.setTrustedPackages(Arrays.asList("org.xfs.core"));
            // connectionFactory.setUserName("admin");
            // connectionFactory.setPassword("admin");

            // connectionFactory.setc
            pool = new PooledConnectionFactory(connectionFactory);
            pool.setMaxConnections(10);
            pool.setMaximumActiveSessionPerConnection(10);

        } catch (Exception e) {
            logger.error("init pool error:{}", e);
        }
        // pool.setConnectionFactory(connectionFactory);
        if (logger.isInfoEnabled()) {
            logger.info("----------------init PooledConnectionFactory ---------------------");
        }
        pool.setMaxConnections(10);
        return pool;
    }

    @Bean
    public JmsTemplate jmsTemplate(PooledConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory);
        jmsTemplate.setDefaultDestinationName(ORDER_QUEUE);
        return jmsTemplate;
    }

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(PooledConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer slc = new SimpleMessageListenerContainer();
        slc.setConcurrentConsumers(8);
        slc.setConcurrency("5-10");
        slc.setDestinationName(ORDER_QUEUE);
        slc.setConnectionFactory(connectionFactory);
        return slc;
    }
}
