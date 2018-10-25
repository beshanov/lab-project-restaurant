package com.labproject.restaurant.controllers;

import com.labproject.restaurant.entities.User;
import com.labproject.restaurant.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("user", new User());
        return mav;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView loginProcess(HttpSession session,
                                     @ModelAttribute("user") User user) {
        ModelAndView mav = null;
        try {
            User user1 = accountService.validateUser(user);
                session.setAttribute("user", user1);
                mav = new ModelAndView("redirect:/profile");
        } catch (IllegalArgumentException e) {
            mav = new ModelAndView("login");
            mav.addObject("message", e.getMessage());
        } catch (NullPointerException e) {
            mav = new ModelAndView("login");
            mav.addObject("message", "can not get info from form");
        }
        return mav;
    }
}
