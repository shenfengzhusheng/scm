package org.xfs.core.platform.config.mq.service;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import org.xfs.core.business.order.model.Order;

@Component
public class MessageSender {

    @Resource
    private JmsTemplate jmsTemplate;

    public void sendMessage(final Order order) {
        this.jmsTemplate.send(new MessageCreator() {

            @Override
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage objMessage = session.createObjectMessage(order);
                return objMessage;
            }
        });
    }
}
