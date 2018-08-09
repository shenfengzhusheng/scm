package org.xfs.core.business.order.service;

import java.util.Map;

import org.xfs.core.business.order.model.Order;

public interface OrderRepository {
    public void putOrder(Order order);

    /**
     * 获取订单
     * project:fm-core
     * @param orderId
     * @return
     * author:xifengshan
     * date:2017年8月31日下午9:12:19
     */
    public Order getOrder(String orderId);

    /**
     * 获取全部订单
     * project:fm-core
     * @return 
     * author:xifengshan 
     * date:2017年8月31日下午9:03:18
     */    
    public Map<String, Order> getAllOrders();
}
