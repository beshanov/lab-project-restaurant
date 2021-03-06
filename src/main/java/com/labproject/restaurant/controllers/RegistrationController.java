package com.labproject.restaurant.controllers;

import com.labproject.restaurant.entities.User;
import com.labproject.restaurant.services.AccountService;
import com.labproject.restaurant.services.validators.UserRegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserRegistrationValidator userValidator;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    @PreAuthorize("!isAuthenticated()")
    public ModelAndView showRegister() {
        ModelAndView mav = new ModelAndView("register");
        mav.addObject("user", new User());
        return mav;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @PreAuthorize("!isAuthenticated()")
    public ModelAndView addUser(@ModelAttribute("user") User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ModelAndView("register");
        }
        accountService.doRegister(user);
        return new ModelAndView("redirect:/login");
    }
}