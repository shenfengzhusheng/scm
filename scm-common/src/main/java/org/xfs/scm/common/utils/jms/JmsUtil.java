package org.xfs.scm.common.utils.jms;



import org.apache.activemq.ScheduledMessage;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;
import java.io.Serializable;

/**
 * Created by 神风逐胜 on 2017/9/24 0024.11:57
 * version:1.0
 */
public class JmsUtil {
    /**
     * 发送文本消息
     * @param jmsTemplate
     * @param destination
     * @param textMessage
     */
    public static void sendMessage(JmsTemplate jmsTemplate, Destination destination, final String textMessage) {
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(textMessage);
            }
        });
    }
    /**
     * 发送对象消息
     * @param jmsTemplate
     * @param destination
     * @param objectMessage
     */
    public static void sendMessage(JmsTemplate jmsTemplate, Destination destination, final Serializable objectMessage) {
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(objectMessage);
            }
        });
    }

    /**
     *  延迟发送对象消息
     * @param jmsTemplate
     * @param destination
     * @param objectMessage
     * @param delayTime
     */
    public static void sendMessageDelay(JmsTemplate jmsTemplate,Destination destination,final Serializable objectMessage,final long delayTime){
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage om=session.createObjectMessage();
                om.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY,delayTime);//延时发送
                om.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD,1*1000);//秒
                om.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT,1);//重试次数
                return null;
            }
        });
    }



    /**
     *  延迟发送对象消息
     * @param jmsTemplate
     * @param destination
     * @param objectMessage
     * @param delayTime
     * @param tryTime
     */
    public static void sendMessageDelayforTimes(JmsTemplate jmsTemplate,Destination destination,final Serializable objectMessage,final long delayTime,int tryTime){
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage om=session.createObjectMessage();
                om.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY,delayTime);//延时发送
                om.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD,1*1000);//秒
                om.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT,tryTime);//重试次数
                return null;
            }
        });
    }

}
