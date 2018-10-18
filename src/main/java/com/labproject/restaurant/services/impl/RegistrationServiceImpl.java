package com.labproject.restaurant.services.impl;

import com.labproject.restaurant.entities.User;
import com.labproject.restaurant.services.RegistrationService;
import com.labproject.restaurant.services.RoleService;
import com.labproject.restaurant.services.UserService;

public class RegistrationServiceImpl implements RegistrationService {

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
        if (user.getPassword().length() < 3) {
            throw new IllegalArgumentException("Password length must be more than 3!");
        }

        if (userService.getByLogin(user.getLogin()).getId() != 0) {
            throw new IllegalArgumentException("This login already exists!");
        } else {
            user.setRole(roleService.getById(1));
            userService.insert(user);
        }
    }
}
