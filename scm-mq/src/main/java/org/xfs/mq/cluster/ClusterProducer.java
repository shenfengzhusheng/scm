package org.xfs.mq.cluster;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.xfs.mq.p2p.Producer;

import javax.jms.*;
import java.util.concurrent.TimeUnit;

public class ClusterProducer {

    //连接工厂
    private ConnectionFactory connectionFactory;
    //连接
    private Connection connection;
    //Session
    private Session session;
    //消息生产者
    private MessageProducer messageProducer;
    private static int count=1;
    public ClusterProducer(){
        try{
            this.connectionFactory=new ActiveMQConnectionFactory("admin",
                    "admin",
                    "failover:(tcp://192.168.0.3:51511,tcp://192.168.0.3:51512,tcp://192.168.0.3:51513)?Randomize=false");
            this.connection=this.connectionFactory.createConnection();
            this.connection.start();
            this.session=this.connection.createSession(Boolean.FALSE,Session.CLIENT_ACKNOWLEDGE);
            this.messageProducer=this.session.createProducer(null);
        }catch (JMSException e){
            e.printStackTrace();;
        }

    }

    public Session getSession() {
        return this.session;
    }

    public void send(){
        try {
            Destination destination=this.session.createQueue("testQueue2");
            MapMessage mapMessage1=this.getSession().createMapMessage();
            mapMessage1.setString("name","张三");
            mapMessage1.setString("age","23");
            mapMessage1.setStringProperty("color","blue");
            mapMessage1.setIntProperty("sal",2200);

            int id=1;
            mapMessage1.setInt("id",id);
            String receiver=id%2==0?"A":"B";
            mapMessage1.setStringProperty("receiver",receiver);


            MapMessage mapMessage2=this.getSession().createMapMessage();
            mapMessage2.setString("name","李四");
            mapMessage2.setString("age","26");
            mapMessage2.setStringProperty("color","blue");
            mapMessage2.setIntProperty("sal",1800);
            id=2;
            mapMessage2.setInt("id",id);
            receiver=id%2==0?"A":"B";
            mapMessage2.setStringProperty("receiver",receiver);

            MapMessage mapMessage3=this.getSession().createMapMessage();
            mapMessage3.setString("name","王五");
            mapMessage3.setString("age","26");
            mapMessage3.setStringProperty("color","blue");
            mapMessage3.setIntProperty("sal",4100);

            id=3;
            mapMessage3.setInt("id",id);
            receiver=id%2==0?"A":"B";
            mapMessage3.setStringProperty("receiver",receiver);
            MapMessage mapMessage4=this.getSession().createMapMessage();
            mapMessage4.setString("name","王五");
            mapMessage4.setString("age","26");
            mapMessage4.setStringProperty("color","red");
            mapMessage4.setIntProperty("sal",400);

            id=4;
            mapMessage4.setInt("id",id);
            receiver=id%2==0?"A":"B";
            mapMessage4.setStringProperty("receiver",receiver);
            this.messageProducer.send(destination,mapMessage1,DeliveryMode.PERSISTENT,2,1000*60*10L);
            this.messageProducer.send(destination,mapMessage2,DeliveryMode.PERSISTENT,3,1000*60*10L);
            this.messageProducer.send(destination,mapMessage3,DeliveryMode.PERSISTENT,6,1000*60*10L);
            this.messageProducer.send(destination,mapMessage4,DeliveryMode.PERSISTENT,9,1000*60*10L);

         //   this.session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    public void sendTextMessage(){
        try {
            Destination destination=this.session.createQueue("cluster");
            TextMessage textMessage=this.session.createTextMessage("我是消息内容："+(count++));
            this.messageProducer.send(destination,textMessage,1,1,1000*60*50);
        //    this.session.commit();
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
        ClusterProducer producer=new ClusterProducer();
        while(true){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
          //  producer.send();
            producer.sendTextMessage();
        }
      //  producer.close();

    }
}
