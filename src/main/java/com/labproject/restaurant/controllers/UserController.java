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
        Object userFromSession = session.getAttribute("user");

        if (userFromSession == null) {
            return new ModelAndView("redirect:/register");
        }

        ModelAndView mav = new ModelAndView("user-settings");
        User userFromDB = userService.getById(((User) userFromSession).getId());
        userFromDB.setRole(((User) userFromSession).getRole());
        mav.addObject("user", userFromDB);
        return mav;
    }

    @RequestMapping(value = "/user-settings", method = RequestMethod.POST)
    public ModelAndView updateUser(HttpSession session, @ModelAttribute("user") User user) {
        Object userFromSession = session.getAttribute("user");

        if (userFromSession == null) {
            return new ModelAndView("redirect:/register");
        }

        try {
            user = userService.userSettingsValidation((User) userFromSession, user);
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage(), e);
            ModelAndView mav = new ModelAndView("user-settings");
            mav.addObject("message", e.getMessage());
            return mav;
        }
        userService.update(user);
        session.setAttribute("user", user);
        return new ModelAndView("user-settings");
    }
}
