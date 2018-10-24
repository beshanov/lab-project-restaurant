package com.labproject.restaurant.services.impl;

import com.labproject.restaurant.entities.User;
import com.labproject.restaurant.services.AccountService;
import com.labproject.restaurant.services.RoleService;
import com.labproject.restaurant.services.UserService;

public class AccountServiceImpl implements AccountService {

    private UserService userService;
    private RoleService roleService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void doRegister(User user) {
        user.setRole(roleService.getById(1));
        userService.insert(user);
    }
}
