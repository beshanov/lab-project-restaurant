package com.labproject.restaurant.controllers;


import com.labproject.restaurant.entities.DishEntity;
import com.labproject.restaurant.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MenuController {
        @Autowired
        private DishService dishService;

    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }

    @RequestMapping(value = "/dishes",method = RequestMethod.GET)
    public ModelAndView showDishes(HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView mav = new ModelAndView("dishes");
        mav.addObject("dishesList", dishService.getAll());
        return mav;
    }

    @RequestMapping(value = "/dishes",method = RequestMethod.POST)
    public void addToCart(HttpServletRequest req, HttpServletResponse resp,  @ModelAttribute("dish") DishEntity dish) {
        System.out.println(dish.getId());
        Object objCartMap = req.getSession().getAttribute("cartMap");
        int count = Integer.parseInt(req.getParameter("count"));
        Map <DishEntity, Integer> cartMap;
        if (objCartMap == null) {
            cartMap = new HashMap<>();
        } else {
            cartMap = (Map<DishEntity, Integer>)objCartMap;
        }
        Integer amounnt = cartMap.get(dish);
        if (amounnt == null) {
            amounnt = 0;
        }
        cartMap.put(dish,amounnt+count);
        System.out.println(cartMap.get(dish));
        req.getSession().setAttribute("cartMap", cartMap);
        resp.setStatus(HttpServletResponse.SC_ACCEPTED);
    }
}
