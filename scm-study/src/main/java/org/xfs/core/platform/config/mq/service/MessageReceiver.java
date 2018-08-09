package org.xfs.core.platform.config.mq.service;

import javax.annotation.Resource;
import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.xfs.core.business.order.model.InventoryResponse;
import org.xfs.core.business.order.service.OrderServiceI;

@Component
public class MessageReceiver {
    public static final Logger logger = LoggerFactory.getLogger(MessageReceiver.class);

    private static final String ORDER_RESPONSE_QUEUE = "order-response-queue";

    @Resource
    private OrderServiceI orderService;

    @JmsListener(destination = ORDER_RESPONSE_QUEUE)
    public void receiveMessage(final Message<InventoryResponse> message) throws JMSException {
        if (logger.isInfoEnabled()) {
            logger.info("++++++++++++++++++++begin receive+++++++++++++++++++++++++++++++++");
        }
        MessageHeaders headers = message.getHeaders();
        if (logger.isInfoEnabled()) {
            logger.info("Application : headers received : {}", headers);
        }
        InventoryResponse response = message.getPayload();
        if (logger.isInfoEnabled()) {
            logger.info("Application : response received : {}", response);
        }
        orderService.updateOrder(response);
        if (logger.isInfoEnabled()) {
            logger.info("+++++++++++++++++++++++end receive++++++++++++++++++++++++++++++");
        }

    }
}
