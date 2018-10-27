package com.labproject.restaurant.services.validators;

import com.labproject.restaurant.entities.User;
import com.labproject.restaurant.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service
public class ProfileValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "firstname.empty", "Firstname field emty or has whitespaces");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "lastname.empty", "Lastname field emty or has whitespaces");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "login.empty", "Login field emty or has whitespaces");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty", "Password field emty or has whitespaces");

        User user = (User) target;
        User userFromDB = userService.getById(user.getId());

        if (!user.getLogin().equals(userFromDB.getLogin())) {
            if (userService.getByLogin(user.getLogin()).getId() != 0) {
                errors.rejectValue("login", "login.alreadyExist",
                        "This login already exists!");
            }
        }

        if (!user.getFirstname().matches("[\\w\\d]{2,20}")) {
            errors.rejectValue("firstname", "firstName.failPattern",
                    "Firstname has wrong format!");
        }

        if (!user.getLastname().matches("[\\w\\d]{3,20}")) {
            errors.rejectValue("lastname", "lastName.failPattern",
                    "Lastname has wrong format!");
        }

        if (!user.getLogin().matches("[\\w\\d]{3,10}")) {
            errors.rejectValue("login", "login.failPattern",
                    "Login has wrong format!");
        }

        if (!user.getPassword().matches("[\\w\\d]{3,8}")) {
            errors.rejectValue("password", "password.failPattern",
                    "Password has wrong format!");
        }
    }
}