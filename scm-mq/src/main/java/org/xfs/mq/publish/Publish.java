package org.xfs.mq.publish;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.xfs.scm.common.utils.date.DateUtil;

import javax.jms.*;

public class Publish {
    //连接工厂
    private ConnectionFactory connectionFactory;
    //连接
    private Connection connection;
    //Session
    private Session session;
    //消息生产者
    private MessageProducer messageProducer;
    public Publish(){
        try{
            this.connectionFactory=new ActiveMQConnectionFactory("jeken","jeken","tcp://127.0.0.1:61616");
            this.connection=this.connectionFactory.createConnection();
            this.connection.start();
            this.session=this.connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);
            this.messageProducer=this.session.createProducer(null);
        }catch (JMSException e){
            e.printStackTrace();;
        }

    }
    public void sendMessage(){
        try {
            Destination destination=this.session.createTopic("message");
            TextMessage textMessage=this.session.createTextMessage("发布消息"+ DateUtil.getNowDayChs());

            this.messageProducer.send(destination,textMessage);

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    public void close(){
        try {
            this.connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[]args){
        Publish publish=new Publish();
        publish.sendMessage();
        publish.close();
    }
}
