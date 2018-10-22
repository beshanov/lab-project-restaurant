package com.labproject.restaurant.controllers;

import com.labproject.restaurant.entities.User;
import com.labproject.restaurant.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@SessionAttributes("user")
public class UserController {
    private static final Logger LOGGER = Logger.getLogger(UserController.class);
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/user-settings", method = RequestMethod.GET)
    public ModelAndView showSettings(@ModelAttribute User user) {
        try {
            Assert.notNull(user);
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage(), e);
        }
        ModelAndView mav = new ModelAndView("user-settings");
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping(value = "/user-settings", method = RequestMethod.POST)
    public ModelAndView updateUser(HttpServletRequest req, HttpServletResponse resp,
                                   @ModelAttribute("user") User user) {
        try {
        userService.update(user);

        }catch (NullPointerException e){
            LOGGER.error(e.getMessage(), e);
        }
        return new ModelAndView("user-settings");
    }
}
