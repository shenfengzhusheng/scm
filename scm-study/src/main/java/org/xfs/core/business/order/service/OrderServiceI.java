package org.xfs.core.business.order.service;

import java.util.Map;

import org.xfs.core.business.order.model.InventoryResponse;
import org.xfs.core.business.order.model.Order;

public interface OrderServiceI {
    public void sendOrder(Order order);

    public void updateOrder(InventoryResponse response);

    public Map<String, Order> getAllOrders();
}
