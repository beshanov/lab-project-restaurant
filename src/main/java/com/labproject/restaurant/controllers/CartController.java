package com.labproject.restaurant.controllers;

import com.labproject.restaurant.entities.Dish;
import com.labproject.restaurant.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public void modifyCart(HttpServletRequest request) {
        Map<Dish, Integer> dishMap;

        if (request.getParameter("deleteId") != null) {
            dishMap = dishService.deleteFromDishMap(request);

        } else {
            dishMap = dishService.addToDishMap(request);
        }

        request.getSession().setAttribute("dishMap", dishMap);
    }
}
