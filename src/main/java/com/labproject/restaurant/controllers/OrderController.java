package com.labproject.restaurant.controllers;

import com.labproject.restaurant.entities.Order;
import com.labproject.restaurant.services.OrderService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class OrderController {

    OrderService orderService;
    //DishService dishService;

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    /*public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }*/

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String displayOrder() {


        return "orderDetails";
    }

    @RequestMapping(value = "/newOrder", method = RequestMethod.POST)
    public String createNewOrder() {


        return "orderDetails";
    }

    @RequestMapping(value = "/modifyOrder", method = RequestMethod.POST)
    public String modifyOrder() {


        return "orderDetails";
    }

    @RequestMapping(value = "/submitOrder", method = RequestMethod.POST)
    public String submitOrder(@ModelAttribute("order") Order order) {
        orderService.createNewOrder(order);

        return "orderDetails";
    }
}
