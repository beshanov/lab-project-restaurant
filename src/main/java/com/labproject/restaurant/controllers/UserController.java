package com.labproject.restaurant.controllers;

import com.labproject.restaurant.entities.User;
import com.labproject.restaurant.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    private static final Logger LOGGER = Logger.getLogger(UserController.class);
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/user-settings", method = RequestMethod.GET)
    public ModelAndView showSettings(HttpSession session) {
        Object user = session.getAttribute("user");
        if (user == null) {
            ModelAndView mav = new ModelAndView("register");
            mav.addObject("user", new User());
            return mav;
        } else {
            ModelAndView mav = new ModelAndView("user-settings");
            User user1 = userService.getById(((User) user).getId());
            mav.addObject("user", (User) user1);
            return mav;
        }
    }

    @RequestMapping(value = "/user-settings", method = RequestMethod.POST)
    public ModelAndView updateUser(HttpSession session, @ModelAttribute("user") User user) {
        ModelAndView mav = new ModelAndView("user-settings");
        if (userService.isLoginExists(user.getLogin())) {
            mav.addObject("message", "This login is already exists!");
        } else {
            userService.update(user);
            session.setAttribute("user", user);
        }
        return mav;
    }
}
