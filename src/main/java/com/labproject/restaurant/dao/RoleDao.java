package com.labproject.restaurant.dao;

import com.labproject.restaurant.entities.Role;

import java.util.List;

public interface RoleDao {
    Role getById(long id);

    Role getRoleByLogin(String login);

    void insert(Role role);

    void update(Role role);

    void delete(Role role);

    List<Role> getAllRoles();
}
