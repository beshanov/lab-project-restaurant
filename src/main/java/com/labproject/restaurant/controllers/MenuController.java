package com.labproject.restaurant.controllers;


import com.labproject.restaurant.entities.Dish;
import com.labproject.restaurant.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MenuController {
    @Autowired
    private DishService dishService;

    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }

    @RequestMapping(value = "/dish", method = RequestMethod.GET)
    public ModelAndView showAllDishes() {
        ModelAndView mav = new ModelAndView("dishes");
        mav.addObject("dishesList", dishService.getAll());
        return mav;
    }

    @RequestMapping(value = "/dish/{dishId}", method = RequestMethod.GET)
    public ModelAndView showDish(@PathVariable long dishId) {
        ModelAndView mav = new ModelAndView("dish");
        mav.addObject("dish", dishService.getById(dishId));
        return mav;
    }


    @RequestMapping(value = "/dish", method = RequestMethod.POST)
    public ModelAndView addNewDish(@ModelAttribute Dish dish) {
        dishService.insert(dish);
        return new ModelAndView("redirect:/dish");
    }

    @RequestMapping(value = "/dish/{dishId}", method = RequestMethod.POST)
    public ModelAndView updateDish(@PathVariable long dishId, @ModelAttribute Dish dish) {
        dishService.update(dish);
        return new ModelAndView("redirect:/dish");
    }

    @RequestMapping(value = "/dish/{dishId}", method = RequestMethod.DELETE)
    public ModelAndView deleteDish(@PathVariable long dishId, @ModelAttribute Dish dish) {
        dishService.delete(dish);
        return new ModelAndView("redirect:/dish");
    }
}
