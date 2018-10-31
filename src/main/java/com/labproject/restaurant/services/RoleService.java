package com.labproject.restaurant.services;

import com.labproject.restaurant.entities.Role;

import java.util.List;

public interface RoleService {

    public Role getById(long id);

    public void insert(Role role);

    public void update(Role role);

    public void delete(Role role);

    Role getRoleByLogin(String login);

    List<Role> getAllRoles();
}
