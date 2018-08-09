package org.xfs.test.base.mq;

import java.util.HashMap;
import java.util.Map;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQMapMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Publisher {
	private static Logger logger=LoggerFactory.getLogger(Publisher.class);
	protected int MAX_DELTA_PERCENT =1;
	protected Map<String,Double>LAST_PRICES=new HashMap<String,Double>();
	protected static int count=10;
	protected static int total;
	
	protected static String brokerURL="tcp://localhost:61616";
	protected static transient ConnectionFactory factory;
	protected transient Connection connection;
	protected transient Session session;
	protected transient MessageProducer producer;
	
	public static void main(String[]args)throws Exception{
		String[]arrs={"key1","key2","key3","key4","key5","key6","key7","key8","key9","key10"};
		Publisher publisher=new Publisher();
		while(total<1000){
			for(int i=0;i<count;i++){
				publisher.sendMessage(arrs);
			}
			total +=count;
			System.out.println("Published '"+count+"' of '"+total+"' price messages");
			try {
				Thread.sleep(1000);  
	        } catch (InterruptedException x) {  
	        }  
	        publisher.close();  

		}
	}
	public Publisher()throws JMSException{
		factory=new ActiveMQConnectionFactory(brokerURL);
		connection=factory.createConnection();
		
		try{
			connection.start();
		}catch(JMSException jmse){
			connection.close();
			logger.info(jmse.getMessage());
			throw jmse;
		}
		session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		producer=session.createProducer(null);
	}
	
	public void close()throws JMSException{
		if(connection!=null){
			connection.close();
		}
	}
	
	protected void sendMessage(String[]stocks)throws JMSException{
		int idx=0;
		while(true){
			idx=(int)Math.round(stocks.length*Math.random());
			if(idx<stocks.length)
				break;
		}
		String stock=stocks[idx];
		Destination destination=session.createTopic("STOCKS."+stock);
		Message message=createStockMessage(stock,session);
		System.out.println("Sending:"+((ActiveMQMapMessage)message).getContentMap()+" on destination: "+destination);
		producer.send(destination,message);
	}

	private Message createStockMessage(String stock, Session session2) throws JMSException {
		Double value=LAST_PRICES.get(stock);
		if(value==null)
			value=new Double(Math.random()*100);
		double oldPrice=value.doubleValue();
		value=new Double(mutatePrice(oldPrice));
		LAST_PRICES.put(stock, value);
		double price=value.doubleValue();
		double offer=price*1.001;
        boolean up = (price > oldPrice);  
		MapMessage message=session.createMapMessage();
		message.setString("stock", stock);
		message.setDouble("price", price);
		message.setDouble("offer", offer);
		message.setBoolean("up", up);
		return message;
	}

	private double  mutatePrice(double price) {
		 double percentChange = (2 * Math.random() * MAX_DELTA_PERCENT) - MAX_DELTA_PERCENT;  
		 return price * (100 + percentChange) / 100;  
	}
}
