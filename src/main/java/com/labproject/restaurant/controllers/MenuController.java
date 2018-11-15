package com.labproject.restaurant.controllers;


import com.labproject.restaurant.entities.Dish;
import com.labproject.restaurant.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MenuController {
    @Autowired
    private DishService dishService;

    @RequestMapping(value = "/dish", method = RequestMethod.GET)
    public ModelAndView showAllDishes() {
        ModelAndView mav = new ModelAndView("dishes");
        mav.addObject("dishesList", dishService.getAll());
        return mav;
    }

    @RequestMapping(value = "/dish/page/{page}", method = RequestMethod.GET)
    public ModelAndView showDishPage(@PathVariable int page,
                                     @RequestParam(name = "deleted", defaultValue = "false") boolean deleted,
                                     @RequestParam(name = "countPerPage", defaultValue = "6") int countPerPage) {
        int maxPage = (int) Math.ceil(dishService.dishesCount(deleted) / (float) countPerPage);
        ModelAndView mav = new ModelAndView("dishPage");
        mav.addObject("dishesList", dishService.getPage(page, countPerPage, deleted));
        mav.addObject("deleted", deleted);
        mav.addObject("currentPage", page);
        mav.addObject("maxPage", maxPage);
        return mav;
    }

    @RequestMapping(value = "/dish/{dishId}", method = RequestMethod.GET)
    public @ResponseBody Dish showDish(@PathVariable long dishId) {
        return dishService.getById(dishId);
    }

    @RequestMapping(value = "/dish", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ModelAndView addNewDish(@ModelAttribute Dish dish) {
        dishService.insert(dish);
        return new ModelAndView("redirect:/dish");
    }

    @RequestMapping(value = "/dish/{dishId}", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ModelAndView updateDish(@PathVariable long dishId, @RequestBody Dish dish) {
        dish.setId(dishId);
        dishService.update(dish);
        return new ModelAndView("redirect:/dish");
    }

    @RequestMapping(value = "/dish/{dishId}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    @ResponseBody
    public void deleteDish(@PathVariable long dishId) {
        Dish dish = dishService.getById(dishId);
        dishService.updateIsDeleted(dish);

    }

    @RequestMapping(value = "/dish/create", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ModelAndView addDish(@ModelAttribute Dish dish) {
        ModelAndView mav = new ModelAndView("newdish");
        mav.addObject("dish", new Dish());
        return mav;
    }
}
