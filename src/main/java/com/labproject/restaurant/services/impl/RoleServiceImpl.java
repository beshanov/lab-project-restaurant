package com.labproject.restaurant.services.impl;

import com.labproject.restaurant.dao.RoleDao;
import com.labproject.restaurant.entities.Role;
import com.labproject.restaurant.services.RoleService;

public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role getById(long id) {
        return roleDao.getById(id);
    }

    @Override
    public void insert(Role role) {
        roleDao.insert(role);
    }

    @Override
    public void update(Role role) {
        roleDao.update(role);
    }

    @Override
    public void delete(Role role) {
        roleDao.delete(role);
    }

}
