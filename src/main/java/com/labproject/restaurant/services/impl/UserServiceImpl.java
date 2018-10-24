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

    @Override
    public User getByLogin(String login) {
        return userDao.getByLogin(login);
    }

    @Override
    public boolean isLoginExist(String login) {
        return userDao.getByLogin(login).getId() != 0;
    }

    @Override
    public User userSettingsValidation(User userFromSession, User user) {
        if (!user.getLogin().equals(userFromSession.getLogin())) {
            if (isLoginExist(user.getLogin())) {
                throw new IllegalArgumentException("This login is already exists!");
            }
        }
        user.setRole(((User) userFromSession).getRole());
        return user;
    }

}
