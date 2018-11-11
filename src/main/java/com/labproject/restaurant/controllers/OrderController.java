package com.labproject.restaurant.controllers;

import com.labproject.restaurant.entities.Dish;
import com.labproject.restaurant.services.DishService;
import com.labproject.restaurant.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getAllOrders() {
        ModelAndView mav = new ModelAndView("orders");
        mav.addObject("orderList", orderService.getOrdersByUser());
        return mav;
    }

    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getOrder(@PathVariable long orderId) {
        ModelAndView mav = new ModelAndView("order");
        mav.addObject("order", orderService.getOrderById(orderId));
        mav.addObject("dishMap", dishService.getAllByOrderId(orderId));
        return mav;
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public String createNewOrder(HttpSession session) {
        if (session.getAttribute("dishMap") != null) {
            orderService.createOrderWithDishes((Map<Dish, Integer>) session.getAttribute("dishMap"));
            session.removeAttribute("dishMap");
        }
        return "redirect:/order";
    }

    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public String deleteOrder(@PathVariable long orderId) {
        orderService.deleteOrderById(orderId);
        return "redirect:/order";
    }

    @RequestMapping(value = "/order/{orderId}/pay", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public String payOrder(@PathVariable long orderId) {
        orderService.setOrderStatus(orderId, 4);
        return "redirect:/order/";
    }

    @PostMapping(value = "/order.setStatus")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public String setStatus(@RequestParam(name = "orderId", defaultValue = "0") long orderId,
                            @RequestParam(name = "statusId", defaultValue = "0") long statusId) {
        if(statusId == 4) return "redirect:/order/" + orderId + "/pay";
        orderService.setOrderStatus(orderId, statusId);
        return "redirect:/order/" + orderId;
    }
}
