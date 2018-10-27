package com.labproject.restaurant.controllers;

import com.labproject.restaurant.entities.Dish;
import com.labproject.restaurant.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class CartController {

    @Autowired
    private DishService dishService;

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public ModelAndView displayCart() {
        return new ModelAndView("cart");
    }

    @RequestMapping(value = "/cart", method = RequestMethod.POST)
    public void addDishToCart(HttpServletRequest request) {
        Map<Dish, Integer> dishMap = dishService.addToDishMap(request);
        request.getSession().setAttribute("dishMap", dishMap);
    }

    @RequestMapping(value = "/cart", method = RequestMethod.DELETE)
    @ResponseBody
    public void removeDishFromCart(HttpServletRequest request) {
        Map<Dish, Integer> dishMap = dishService.deleteFromDishMap(request);
        request.getSession().setAttribute("dishMap", dishMap);
    }
}
