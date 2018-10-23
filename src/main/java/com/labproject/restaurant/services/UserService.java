package com.labproject.restaurant.services;

import com.labproject.restaurant.entities.User;

public interface UserService {

    User getById(long id);

    void insert(User user);

    void update(User user);

    void delete(User user);

    User getByLogin(String login);

    boolean isLoginExist(String login);
}
