package org.xfs.netty.server.advance.service;

import com.alibaba.fastjson.JSON;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.xfs.api.model.WebMessage;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

public class SendMessageTask implements Runnable {
    private JmsTemplate jmsTemplate;
    private WebMessage webMessage;
    public SendMessageTask(JmsTemplate jmsTemplate,WebMessage request){
        this.jmsTemplate=jmsTemplate;
        this.webMessage=request;
    }
    @Override
    public void run() {
        if(this.webMessage!=null && this.jmsTemplate!=null){
            this.send(this.webMessage);
        }
    }

    private void send(WebMessage webMessage){
      this.jmsTemplate.send(new MessageCreator() {
          @Override
          public Message createMessage(Session session) throws JMSException {
              return session.createTextMessage(JSON.toJSONString(webMessage));
          }
      });

    }
}
