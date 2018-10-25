package com.labproject.restaurant.controllers;

import com.labproject.restaurant.entities.User;
import com.labproject.restaurant.services.UserService;
import com.labproject.restaurant.services.validators.ProfileValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class ProfileController {

    private static final Logger LOGGER = Logger.getLogger(ProfileController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private ProfileValidator profileValidator;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView showSettings(HttpSession session) {
        Object userFromSession = session.getAttribute("user");

        if (userFromSession == null) {
            return new ModelAndView("redirect:/login");
        }

        ModelAndView mav = new ModelAndView("profile");
        User userFromDB = userService.getById(((User) userFromSession).getId());
        userFromDB.setRole(((User) userFromSession).getRole());
        mav.addObject("user", userFromDB);
        return mav;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public ModelAndView updateUser(HttpSession session, @ModelAttribute("user") User user,
                                   BindingResult bindingResult) {
        Object userFromSession = session.getAttribute("user");
        if (userFromSession == null) {
            return new ModelAndView("redirect:/login");
        }

        user.setId(((User) userFromSession).getId());
        user.setRole(((User) userFromSession).getRole());

        profileValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ModelAndView("profile");
        }

        userService.update(user);
        session.setAttribute("user", user);
        return new ModelAndView("redirect:/profile");
    }
}
