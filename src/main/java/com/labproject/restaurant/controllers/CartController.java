package com.labproject.restaurant.controllers;

import com.labproject.restaurant.entities.Dish;
import com.labproject.restaurant.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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
    public void addToCart(HttpServletRequest request) {
        if (request.getParameter("deleteId") != null) {
            long deleteId = Long.valueOf(request.getParameter("deleteId"));
            deleteFromCart(request, deleteId);
            return;
        }

        long dishId = Long.valueOf(request.getParameter("id"));
        int count = Integer.valueOf(request.getParameter("count"));
        Dish dish = dishService.getById(dishId);

        Map<Dish, Integer> dm = (Map<Dish, Integer>) request.getSession().getAttribute("dishMap");

        if (dm == null) {
            dm = new HashMap<>();
        }

        if (dish != null && count > 0) {
            if (dm.containsKey(dish)) {
                dm.put(dish, dm.get(dish) + count);
            } else {
                dm.put(dish, count);
            }
        }

        request.getSession().setAttribute("dishMap", dm);
    }

    private void deleteFromCart(HttpServletRequest request, long deleteId) {
        Map<Dish, Integer> dm = (Map<Dish, Integer>) request.getSession().getAttribute("dishMap");

        if (dm != null) {
            for (Map.Entry<Dish, Integer> e : dm.entrySet()) {
                if (e.getKey().getId() == deleteId) {
                    dm.remove(e.getKey());
                    break;
                }
            }
        }

        request.getSession().setAttribute("dishMap", dm);
    }
}
