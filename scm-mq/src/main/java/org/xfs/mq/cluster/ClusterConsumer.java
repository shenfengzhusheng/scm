package org.xfs.mq.cluster;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ClusterConsumer {
    //选择器1
    private final String SELECTOR_0="age>25";
    private final String SELECTOR_1="sal>2000";
    private final String SELECTOR_2="color= 'blue' AND sal>2000";



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
    public ClusterConsumer(){
        try {
            this.connectionFactory=new ActiveMQConnectionFactory("admin",
                    "admin",
                    "failover:(tcp://192.168.0.3:51511,tcp://192.168.0.3:51512,tcp://192.168.0.3:51513)?Randomize=false");
            this.connection=this.connectionFactory.createConnection();
            this.connection.start();
            this.session=this.connection.createSession(Boolean.FALSE,Session.CLIENT_ACKNOWLEDGE);

            this.destination=this.session.createQueue("cluster");
            this.messageConsumer=this.session.createConsumer(this.destination);

            //创建消费者的时侯，发生了变化
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

    public void receiver(){
        try {
            this.messageConsumer.setMessageListener(new Listener());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    static class Listener implements MessageListener{
        public Listener(){

        }
        @Override
        public void onMessage(Message message) {
            try {
                message.acknowledge();
                if(message instanceof  TextMessage){
                    TextMessage ret=(TextMessage)message;
                    System.out.println("文本消息:"+ret.getText());
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
        ClusterConsumer consumer=new ClusterConsumer();
        while (true){
            consumer.receiver();
        }

    }

}
