package com.labproject.restaurant.services;

import com.labproject.restaurant.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{

    /*boolean registration(User user);

    Optional<User> authorization(User user);*/

    User getById(long id);

    void insert(User user);

    void update(User user);

    void delete(User user);

    User getByLogin(String login);

    boolean isLoginExist(String login);

    User userSettingsValidation(User userFromSession, User user);
}
