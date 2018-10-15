package com.labproject.restaurant.dao;

import com.labproject.restaurant.entities.RoleEntity;

public interface RoleDao {
    public RoleEntity getById(long id);

    public void insert(RoleEntity role);

    public void update(RoleEntity role);

    public void delete(RoleEntity role);
}
