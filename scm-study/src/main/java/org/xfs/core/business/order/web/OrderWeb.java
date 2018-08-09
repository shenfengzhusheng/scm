package org.xfs.core.business.order.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xfs.core.business.order.model.Order;
import org.xfs.core.business.order.service.OrderServiceI;

@Controller
@RequestMapping("/order")
public class OrderWeb {
    @Autowired
    OrderServiceI orderService;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String prepareProduct(ModelMap model) {
        return "/order/orderIndex";
    }

    @RequestMapping(value = {"/newOrder"}, method = RequestMethod.GET)
    public String prepareOrder(ModelMap model) {
        Order order = new Order();
        model.addAttribute("order", order);
        return "/order/order";
    }

    @RequestMapping(value = {"/newOrder"}, method = RequestMethod.POST)
    public String sendOrder(@Valid Order order, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "/order/order";
        }
        orderService.sendOrder(order);
        model.addAttribute("success", "Order for " + order.getProductName() + " registered.");
        return "/order/ordersuccess";
    }

    @RequestMapping(value = {"/checkStatus"}, method = RequestMethod.GET)
    public String checkOrderStatus(ModelMap model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "/order/orderStatus";
    }
}
