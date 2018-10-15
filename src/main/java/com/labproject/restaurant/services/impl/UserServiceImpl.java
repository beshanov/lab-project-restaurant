package com.labproject.restaurant.services.impl;

import com.labproject.restaurant.dao.UserDao;
import com.labproject.restaurant.entities.User;
import com.labproject.restaurant.services.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao;


    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getById(long id) {
        return userDao.getById(id);
    }

    @Override
    public void insert(User user) {
        userDao.insert(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }
}
