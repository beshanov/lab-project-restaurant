package com.labproject.restaurant.services.impl;

import com.labproject.restaurant.entities.Role;
import com.labproject.restaurant.entities.User;
import com.labproject.restaurant.services.AccountService;
import com.labproject.restaurant.services.RoleService;
import com.labproject.restaurant.services.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A service layer class for {@link User} account functionality that implements the
 * {@link AccountService} interface
 *
 * @author Igor Pevtsov
 * @author Alexandr Zorin
 * @author Vsevolod Beshanov
 * @author Zhanna Fedorova
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Override
    public void doRegister(User user) {
        user.setRole(roleService.getById(2));
        userService.insert(user);
    }

    @Override
    public User validateUser(User user) {
        User existingUser = userService.getByLogin(user.getLogin());
        Role role = roleService.getRoleByLogin(existingUser.getLogin());
        existingUser.setRole(role);
        if (!StringUtils.equals(existingUser.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Login or password is incorrect");
        } else return existingUser;
    }
}