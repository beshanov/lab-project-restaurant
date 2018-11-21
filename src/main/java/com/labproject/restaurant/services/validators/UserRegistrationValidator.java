package com.labproject.restaurant.services.validators;

import com.labproject.restaurant.entities.User;
import com.labproject.restaurant.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * An implementation of {@link Validator} interface which validates
 * the various {@link String} properties of a {@link User} instance
 * for inserting to the data storage
 *
 * @author Igor Pevtsov
 * @author Alexandr Zorin
 * @author Vsevolod Beshanov
 * @author Zhanna Fedorova
 * @see Errors
 * @see ValidationUtils
 * @see User
 */
@Service
public class UserRegistrationValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        String firstName = user.getFirstname().trim();
        String lastName = user.getLastname().trim();
        String login = user.getLogin().trim();
        String password = user.getPassword().trim();

        if (userService.getByLogin(login).getId() != 0) {
            errors.rejectValue("login", "login.alreadyExist",
                    "This login already exists!");
        }

        if (firstName.length() < 2 || firstName.length() > 10) {
            errors.rejectValue("firstname", "firstName.failLength",
                    "First name must be between 2 and 10 symbols!");
        }
        if (!firstName.matches("[\\w\\d]*")) {
            errors.rejectValue("firstname", "firstName.failPattern",
                    "First name mustn't contains any spaces or non latin characters!");
        }


        if (lastName.length() < 3 || lastName.length() > 20) {
            errors.rejectValue("lastname", "lastName.failLength",
                    "Last name must be between 3 and 20 symbols!");
        }
        if (!lastName.matches("[\\w\\d]*")) {
            errors.rejectValue("lastname", "lastName.failPattern",
                    "Last name mustn't contains any spaces or non latin characters!");
        }

        if (login.length() < 3 || login.length() > 10) {
            errors.rejectValue("login", "login.failLength",
                    "Login must be between 3 and 10 symbols!");
        }
        if (!login.matches("[\\w\\d]*")) {
            errors.rejectValue("login", "login.failPattern",
                    "Login mustn't contains any spaces or non latin characters!");
        }

        if (password.length() < 3 || password.length() > 8) {
            errors.rejectValue("password", "password.failLength",
                    "Password must be between 3 and 8 symbols!");
        }
        if (!password.matches("[\\w\\d]*")) {
            errors.rejectValue("password", "password.failPattern",
                    "Password name mustn't contains any spaces or non latin characters!");
        }

    }
}
