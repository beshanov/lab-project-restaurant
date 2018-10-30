package com.labproject.restaurant.services.impl;

import com.labproject.restaurant.dao.RoleDao;
import com.labproject.restaurant.entities.Role;
import com.labproject.restaurant.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

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

    @Override
    public Role getRoleByLogin(String login) {
        return roleDao.getRoleByLogin(login);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }
}
