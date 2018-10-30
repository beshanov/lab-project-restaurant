package com.labproject.restaurant.controllers;

import com.labproject.restaurant.entities.User;
import com.labproject.restaurant.services.DishService;
import com.labproject.restaurant.services.OrderService;
import com.labproject.restaurant.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private DishService dishService;

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public ModelAndView getAllOrders() {
        ModelAndView mav = new ModelAndView("orders");
        User loggedUser = userService.getLoggedUser();
        mav.addObject("orderList",
                orderService.getOrdersByUser(loggedUser));

        return mav;
    }

    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.GET)
    public ModelAndView getOrder(@PathVariable long orderId) {
        ModelAndView mav = new ModelAndView("order");

        mav.addObject("order", orderService.getOrderById(orderId));
        mav.addObject("dishMap", dishService.getAllByOrderId(orderId));

        return mav;
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public ModelAndView createNewOrder(HttpSession session) {
        orderService.createOrderWithDishes(session.getAttribute("dishMap"));

        return new ModelAndView("order");
    }

    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.DELETE)
    public ModelAndView deleteOrder(@PathVariable long orderId) {
        ModelAndView mav = new ModelAndView("redirect:/cart");
        orderService.deleteOrderById(orderId);

        return mav;
    }
}
