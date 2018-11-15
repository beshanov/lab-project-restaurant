package com.labproject.restaurant.services;

import com.labproject.restaurant.entities.Role;

import java.util.List;

public interface RoleService {

    Role getById(long id);

    void insert(Role role);

    void update(Role role);

    void delete(Role role);

    Role getRoleByLogin(String login);

    List<Role> getAllRoles();
}
