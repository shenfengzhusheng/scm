package org.xfs.netty.server.advance.service;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.xfs.api.model.WebMessage;

import javax.annotation.Resource;

@Service
public class MqService {
    @Resource
    private JmsTemplate jmsTemplate;
    @Resource
    private ThreadPoolTaskExecutor threadPool;

    public void sendMessage(final WebMessage request){

        this.threadPool.execute(new SendMessageTask(jmsTemplate,request));
    }
}
