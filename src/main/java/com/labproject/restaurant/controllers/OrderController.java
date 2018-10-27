package com.labproject.restaurant.controllers;

import com.labproject.restaurant.entities.Dish;
import com.labproject.restaurant.entities.Order;
import com.labproject.restaurant.entities.User;
import com.labproject.restaurant.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

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
    public ModelAndView createNewOrder(HttpSession session) {
        orderService.createOrderWithDishes(((User) session.getAttribute("user")).getId(),
                (Map<Dish, Integer>) session.getAttribute("dishMap"));

        return new ModelAndView("redirect:/dish");
    }

    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteOrder(@PathVariable long orderId) {
        orderService.deleteOrderById(orderId);
    }
}
