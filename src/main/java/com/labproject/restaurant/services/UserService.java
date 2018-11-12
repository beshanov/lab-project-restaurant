package com.labproject.restaurant.services;

import com.labproject.restaurant.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User getById(long id);

    void insert(User user);

    void updateDetails(User user);

    void updateRole(long userId, long roleId);

    void updatePassword(User user, String newPassword);

    void delete(User user);

    User getByLogin(String login);

    boolean isLoginExist(String login);

    User getLoggedUser();

    List<User> getAllUsers();

    List<User> getAllUsers(boolean full);

    boolean isValidOldPasssword(User loggedUser, String oldPassword);
}
