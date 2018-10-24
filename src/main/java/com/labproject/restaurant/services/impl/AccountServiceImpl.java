package com.labproject.restaurant.services.impl;

import com.labproject.restaurant.entities.Role;
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

    @Override
    public User validateUser(User user) {
        User user1 = userService.getByLogin(user.getLogin());
        Role role = roleService.getRoleByLogin(user1.getLogin());
        user1.setRole(role);
        if (user1 == null || !(user1.getPassword().equals(user.getPassword()))) {
            throw new IllegalArgumentException("Login or password is incorrect");
        } else return user1;
    }
}