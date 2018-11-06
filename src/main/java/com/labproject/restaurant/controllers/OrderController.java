package com.labproject.restaurant.controllers;

import com.labproject.restaurant.entities.Dish;
import com.labproject.restaurant.services.DishService;
import com.labproject.restaurant.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private DishService dishService;

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public ModelAndView getAllOrders() {
        ModelAndView mav = new ModelAndView("orders");
        mav.addObject("orderList", orderService.getOrdersByUser());
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
    public String createNewOrder(HttpSession session) {
        if (session.getAttribute("dishMap") != null) {
            orderService.createOrderWithDishes((Map<Dish, Integer>) session.getAttribute("dishMap"));
            session.removeAttribute("dishMap");
        }
        return "redirect:/order";
    }

    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.DELETE)
    public String deleteOrder(@PathVariable long orderId) {
        orderService.deleteOrderById(orderId);
        return "redirect:/order";
    }

    @RequestMapping(value = "/orderModifyStatus", method = RequestMethod.POST)
    public void modifyStatus(@RequestParam(name = "orderId", defaultValue = "0") long orderId,
                             @RequestParam(name = "statusId", defaultValue = "0") long statusId) {
        orderService.modifyOrderStatus(orderId, statusId);

        //return "redirect:/order";
    }
}
