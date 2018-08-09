package org.xfs.mq.publish;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class PubConsumer2 {
    //1、连接工厂
    private ConnectionFactory connectionFactory;
    //2、连接
    private Connection connection;
    //3、Session会话
    private Session session;
    //4、目的队列
    private Destination destination;
    //5、消费者
    private MessageConsumer messageConsumer;
    public PubConsumer2(){
        try {
            this.connectionFactory=new ActiveMQConnectionFactory("jeken","jeken","tcp://127.0.0.1:61616");
            this.connection=this.connectionFactory.createConnection();
            this.connection.start();
            this.session=this.connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);


            //创建消费者的时侯，发生了变化
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void receive(){
        try {
            Destination destination=this.session.createTopic("message");
            messageConsumer=this.session.createConsumer(destination);
            this.messageConsumer.setMessageListener(new PubListener());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    static class PubListener implements MessageListener{

        @Override
        public void onMessage(Message message) {
            try {
                message.acknowledge();
                if(message instanceof  TextMessage){
                    TextMessage ret=(TextMessage)message;
                    System.out.println("消费者2收到消息:"+ret.getText());
                }
                if(message instanceof  MapMessage){
                    MapMessage ret=(MapMessage)message;
                    System.out.println(ret.toString());
                    System.out.println(ret.getString("name"));
                    System.out.println(ret.getString("age"));

                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[]args){
        PubConsumer2 pubConsumer2=new PubConsumer2();
        pubConsumer2.receive();
    }
}
