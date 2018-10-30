package com.labproject.restaurant.dao;

import com.labproject.restaurant.entities.User;

import java.util.List;

public interface UserDao {

    User getById(long id);

    User getByLogin(String login);

    void insert(User user);

    void update(User user);

    void delete(User user);

    List<User> getAllUsers(boolean full);
}
