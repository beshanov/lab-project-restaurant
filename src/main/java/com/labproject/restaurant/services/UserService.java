package com.labproject.restaurant.services;

import com.labproject.restaurant.entities.User;

public interface UserService {

    public User getById(long id);

    public void insert(User user);

    public void update(User user);

    public void delete(User user);

    public User getByLogin(String login);


}
