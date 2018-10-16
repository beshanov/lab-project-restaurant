package com.labproject.restaurant.services;

import com.labproject.restaurant.entities.Role;

public interface RoleService {

    public Role getById(long id);

    public void insert(Role role);

    public void update(Role role);

    public void delete(Role role);
}
