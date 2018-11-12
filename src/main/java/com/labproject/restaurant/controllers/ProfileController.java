package com.labproject.restaurant.controllers;

import com.labproject.restaurant.entities.User;
import com.labproject.restaurant.services.UserService;
import com.labproject.restaurant.services.validators.ProfileValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController {

    private static final Logger LOGGER = Logger.getLogger(ProfileController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private ProfileValidator profileValidator;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView showSettings() {
        ModelAndView mav = new ModelAndView("profile");
        User loggedUser = userService.getLoggedUser();
        mav.addObject("user", loggedUser);
        return mav;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public ModelAndView updateUser(@ModelAttribute("user") User user,
                                   BindingResult bindingResult) {
        profileValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            ModelAndView mav = new ModelAndView("profile");
            mav.addObject("user", user);
            return mav;
        }
        userService.updateDetails(user);
        return new ModelAndView("redirect:/profile");
    }

    @RequestMapping(value = "/profile/updatePassword", method = RequestMethod.POST)
    public ResponseEntity<String> updatePassword(@RequestParam("oldPassword") String oldPassword,
                                                 @RequestParam("newPassword") String newPassword) {
        User loggedUser = userService.getLoggedUser();
        if (!userService.isValidOldPasssword(loggedUser, oldPassword)) {
            return ResponseEntity.badRequest().body("");
        }
        userService.updatePassword(loggedUser, newPassword);
        return ResponseEntity.ok().body("");
    }
}
