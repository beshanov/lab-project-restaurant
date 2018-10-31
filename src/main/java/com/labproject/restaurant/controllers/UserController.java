package com.labproject.restaurant.controllers;

import com.labproject.restaurant.entities.User;
import com.labproject.restaurant.services.RoleService;
import com.labproject.restaurant.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;


@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView showUsers() {
        ModelAndView mav = new ModelAndView("users");
        mav.addObject("usersList", userService.getAllUsers(true));
        mav.addObject("rolesList", roleService.getAllRoles());
        return mav;
    }

    @RequestMapping(value = "/user/{userID}", method = RequestMethod.POST)
    public void updateUserRole(@PathVariable long userID, Long roleId, HttpServletResponse resp) {
        User user = userService.getById(userID);
        user.setRole(roleService.getById(roleId));
        userService.update(user);
        resp.setStatus(HttpServletResponse.SC_ACCEPTED);
    }
}