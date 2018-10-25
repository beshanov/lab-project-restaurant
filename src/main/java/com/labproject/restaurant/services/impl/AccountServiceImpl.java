package com.labproject.restaurant.services.impl;

import com.labproject.restaurant.entities.Role;
import com.labproject.restaurant.entities.User;
import com.labproject.restaurant.services.AccountService;
import com.labproject.restaurant.services.RoleService;
import com.labproject.restaurant.services.UserService;
import org.apache.commons.lang3.StringUtils;

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
        User existingUser = userService.getByLogin(user.getLogin());
        Role role = roleService.getRoleByLogin(existingUser.getLogin());
        existingUser.setRole(role);
        if (existingUser == null || !(StringUtils.equals(existingUser.getPassword(),user.getPassword()))) {
            throw new IllegalArgumentException("Login or password is incorrect");
        } else return existingUser;
    }
}