package com.labproject.restaurant.controllers;

import com.labproject.restaurant.entities.User;
import com.labproject.restaurant.services.RoleService;
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
    private RoleService roleService;

    @Autowired
    private ProfileValidator profileValidator;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView showSettings(HttpSession session) {

        ModelAndView mav = new ModelAndView("profile");
        User loggedUser = userService.getLoggedUser();
        loggedUser.setRole(roleService.getById(loggedUser.getRole().getId()));
        mav.addObject("user", loggedUser);
        return mav;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public ModelAndView updateUser(HttpSession session, @ModelAttribute("user") User user,
                                   BindingResult bindingResult) {

        User loggedUser = userService.getLoggedUser();
        loggedUser.setRole(roleService.getById(loggedUser.getRole().getId()));
        profileValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ModelAndView("profile");
        }

        userService.update(user);
        session.setAttribute("user", user);
        return new ModelAndView("redirect:/profile");
    }
}
