package com.labproject.restaurant.controllers;

import com.labproject.restaurant.entities.Dish;
import com.labproject.restaurant.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class CartController {

    @Autowired
    private DishService dishService;

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    @PreAuthorize("!hasAuthority('ADMINISTRATOR')")
    public ModelAndView displayCart() {
        return new ModelAndView("cart");
    }

    @RequestMapping(value = "/cart.size", method = RequestMethod.GET)
    @PreAuthorize("!hasAuthority('ADMINISTRATOR')")
    @ResponseBody
    public String getCartSize(HttpSession session) {
        if (session.getAttribute("cartSize") == null) {
            session.setAttribute("cartSize", 0);
            return "0";
        }
        return session.getAttribute("cartSize").toString();
    }

    @RequestMapping(value = "/cart", method = RequestMethod.POST)
    @PreAuthorize("!hasAuthority('ADMINISTRATOR')")
    public void addDishToCart(HttpServletRequest request) {
        Map<Dish, Integer> dishMap = dishService.addToDishMap(request);
        request.getSession().setAttribute("dishMap", dishMap);
    }

    @RequestMapping(value = "/cart/{dishId}", method = RequestMethod.DELETE)
    @PreAuthorize("!hasAuthority('ADMINISTRATOR')")
    public void removeDishFromCart(HttpSession session, @PathVariable long dishId) {
        Map<Dish, Integer> dishMap = dishService.deleteFromDishMap(session, dishId);
        session.setAttribute("dishMap", dishMap);
    }
}
