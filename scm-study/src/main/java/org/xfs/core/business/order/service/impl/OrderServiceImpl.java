package org.xfs.core.business.order.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xfs.core.business.order.model.InventoryResponse;
import org.xfs.core.business.order.model.Order;
import org.xfs.core.business.order.model.OrderStatus;
import org.xfs.core.business.order.service.OrderRepository;
import org.xfs.core.business.order.service.OrderServiceI;
import org.xfs.core.platform.config.mq.service.MessageSender;
import org.xfs.core.util.IdGenerator;

@Service
public class OrderServiceImpl implements OrderServiceI {
    static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MessageSender messageSender;

    @Override
    public void sendOrder(Order order) {
        if (logger.isInfoEnabled()) {
            logger.info("------------------------------begin send----------------------------");
        }
        order.setOrderId(IdGenerator.generator());
        order.setStatus(OrderStatus.CREATED);
        orderRepository.putOrder(order);
        logger.info("Application : sending order request {}", order);
        messageSender.sendMessage(order);
        if (logger.isInfoEnabled()) {
            logger.info("------------------------------end send------------------------------");
        }
    }

    @Override
    public void updateOrder(InventoryResponse response) {
        Order order = orderRepository.getOrder(response.getOrderId());
        if (response.getReturnCode() == 200) {
            order.setStatus(OrderStatus.CONFIRMED);
        } else if (response.getReturnCode() == 300) {
            order.setStatus(OrderStatus.FAILED);
        } else {
            order.setStatus(OrderStatus.PENDING);
        }
        orderRepository.putOrder(order);
    }

    @Override
    public Map<String, Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }

}
