package com.labproject.restaurant.dao;

import com.labproject.restaurant.entities.Role;

public interface RoleDao {
    public Role getById(long id);

    public Role getRoleByLogin(String login);

    public void insert(Role role);

    public void update(Role role);

    public void delete(Role role);

    Role getRoleByLogin(String login);
}
