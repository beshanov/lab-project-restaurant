package com.labproject.restaurant.dao;

import com.labproject.restaurant.entities.UserEntity;

public interface UserDao {

    public UserEntity getById(long id);

    public void insert(UserEntity user);

    public void update(UserEntity user);

    public void delete(UserEntity user);
}
