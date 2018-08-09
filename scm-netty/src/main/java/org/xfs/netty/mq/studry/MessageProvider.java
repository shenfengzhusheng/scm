package org.xfs.netty.mq.studry;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MessageProvider {


    public static void main(String[]args){
        /**
         * 第一步：建立connectionFactory工厂对象，需要填入用户名，密码，以及连接地址， 均使用默认即可，默认端口为tcp://127.0.0.1:61616
         */
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("jeken",
                "jeken",
                "tcp://localhost:61616");
        /**
         * 第二步：通过connectionFactory工厂对象建立Connection连接,并且调用Connection的start方法开启连接，Connection默认是关闭的
         */
        Connection connection=null;
        try {
            connection=connectionFactory.createConnection();
            connection.start();


            /**
             * 第三步：通过Connection对象创建session会话(上下文环境对象),用于接收消息
             */
            Session session=connection.createSession(
                    true,//是否启用事务
                    Session.CLIENT_ACKNOWLEDGE//为签收模式,一般设置成自动签收
            );
            /**
             * 第四步：通过Session创建一个Destination对象，指的是客户端用来指定消息目标来源的对象,
             * 在P2P模式中，Destation被称作Quueu即列队,
             * 在Pub/Sub模式,Destination被称作Topic即主题,
             * 在程序中可以使用多个Queue和Topic
             */
            Destination destination=session.createQueue("testQueue");
            /**
             * 第五步：我们需要通过Session对象创建消息的发送或接收对象（生产者或消费者)MessageProducer/MessageConsumers
             */
            MessageProducer messageProducer=session.createProducer(destination);
            /**
             * 第六步：我们可以使用MessageProducer的setDeliveryMode方法为其设置持久特性和非持久特性
             */
            messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);//持久化
            /**
             * 第七步:最后我们使用JMS规范的TextMessage形式创建数据(通过Session对象),并且MessageProducer的send方未能去发送数数据,同理客户端使用MessageConsume消费消息
             */
             for(int i=1;i<=100;i++){
                 TextMessage textMessage=session.createTextMessage("hello world!"+i);//内容

                 System.out.println("provider create message+"+textMessage.getText());
                 messageProducer.send(destination,        //目的地
                         textMessage,                     //消息
                         DeliveryMode.NON_PERSISTENT,  //是持久
                         i,                              //优先级【由低到高】
                         1000*60*2                   //消息的有效期
                 );





             }
            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
