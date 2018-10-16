package com.labproject.restaurant.dao;

import com.labproject.restaurant.entities.User;

public interface UserDao {

    public User getById(long id);

    public void insert(User user);

    public void update(User user);

    public void delete(User user);
}
