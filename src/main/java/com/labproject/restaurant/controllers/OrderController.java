package com.labproject.restaurant.controllers;

import com.labproject.restaurant.entities.Order;
import com.labproject.restaurant.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public ModelAndView getAllOrders() {
        ModelAndView mav = new ModelAndView("profile");

        mav.addObject("orderList", orderService.getAllOrders());

        return mav;
    }

    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.GET)
    public ModelAndView getOrder(@PathVariable long orderId) {
        ModelAndView mav = new ModelAndView("profile");
        Order order = orderService.getOrderById(orderId);

        mav.addObject("order", order);

        return mav;
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public ModelAndView createNewOrder(@ModelAttribute("order") Order order) {
        orderService.createNewOrder(order);

        return new ModelAndView("profile");
    }

    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.DELETE)
    public void deleteOrder(@PathVariable long orderId) {
        orderService.deleteOrderById(orderId);
    }
}
