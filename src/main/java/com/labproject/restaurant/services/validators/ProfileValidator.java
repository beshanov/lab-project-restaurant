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

        if (user.getFirstname().length() < 2 || user.getFirstname().length() > 20) {
            errors.rejectValue("firstname", "firstName.failLength",
                    "First name must be between 2 and 10 symbols!");
        }

        if (!user.getFirstname().matches("[\\w\\d]*")) {
            errors.rejectValue("firstname", "firstName.failPattern",
                    "First name mustn't contains any spaces or non latin characters!");
        }

        if (user.getLastname().length() < 3 || user.getLastname().length() > 20) {
            errors.rejectValue("lastname", "lastName.failLength",
                    "Last name must be between 3 and 20 symbols!");
        }

        if (!user.getLastname().matches("[\\w\\d]*")) {
            errors.rejectValue("lastname", "lastName.failPattern",
                    "Last name mustn't contains any spaces or non latin characters!");
        }

        if (user.getLogin().length() < 3 || user.getLogin().length() > 10) {
            errors.rejectValue("login", "login.failLength",
                    "Login must be between 3 and 10 symbols!");
        }

        if (!user.getLogin().matches("[\\w\\d]*")) {
            errors.rejectValue("login", "login.failPattern",
                    "Login mustn't contains any spaces or non latin characters!");
        }

        if (user.getPassword().length() < 3 || user.getPassword().length() > 8) {
            errors.rejectValue("password", "password.failLength",
                    "Password must be between 3 and 8 symbols!");
        }

        if (!user.getPassword().matches("[\\w\\d]*")) {
            errors.rejectValue("password", "password.failPattern",
                    "Password name mustn't contains any spaces or non latin characters!");
        }
    }
}
