package com.labproject.restaurant.services;

import com.labproject.restaurant.entities.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * A service layer interface that provides the CRUD functionality
 * for the {@link User} entity
 *
 * @author Igor Pevtsov
 * @author Alexandr Zorin
 * @author Vsevolod Beshanov
 * @author Zhanna Fedorova
 * @see User
 */
public interface UserService extends UserDetailsService {

    /**
     * Returns a {@link User} object
     * by the specified id
     *
     * @param id a primary key of User table
     * @return the User object at the specified id
     * @see User
     */
    User getById(long id);

    /**
     * Inserts the {@link User} object into a data storage
     *
     * @param user the User entity
     * @see User
     */
    void insert(User user);

    /**
     * Updates some details of the {@link User} object in a data storage
     *
     * @param user the User entity
     * @see User
     */
    void updateDetails(User user);

    /**
     * Updates role field of the {@link User} object in a data storage
     *
     * @param userId the User id
     * @param roleId the Role id
     * @see User
     */
    void updateRole(long userId, long roleId);

    /**
     * Updates password field of the {@link User} object in a data storage
     *
     * @param user        the User entity
     * @param newPassword the String that will replase old password
     * @see User
     */
    void updatePassword(User user, String newPassword);

    /**
     * Deletes the {@link User} object from data storage
     * by a specified id
     *
     * @param user the User entity
     * @see User
     */
    void delete(User user);

    /**
     * Returns a {@link User} object from a data storage
     * by the specified login of {@link User}
     *
     * @param login a unique identificator of User
     * @return the User object at the specified login
     * @see User
     */
    User getByLogin(String login);

    /**
     * Returns <tt>true</tt> if {@link User} object with specified login
     * already exists in the data storage
     *
     * @param login a unique identificator of User
     * @return <tt>true</tt> if {@link User} object with specified login
     * already exists in the data storage
     * @see User
     */
    boolean isLoginExist(String login);

    /**
     * Returns a {@link User} object from a {@link SecurityContextHolder}
     *
     * @return a {@link User} object from a {@link SecurityContextHolder}
     * @see User
     * @see SecurityContextHolder
     */
    User getLoggedUser();

    /**
     * Returns a List of {@link User} objects from a data storage
     *
     * @return the list of User objects
     * @see User
     */
    List<User> getAllUsers();

    /**
     * Returns a List of {@link User} objects from a data storage
     *
     * @param full is needed the full loading of {@link User} objects
     * @return the list of User objects
     * @see User
     */
    List<User> getAllUsers(boolean full);

    /**
     * Returns <tt>true</tt> if {@link User} oldPassword String matches with
     * loggedUser password
     *
     * @param loggedUser  the User entity
     * @param oldPassword the String for validation
     * @return <tt>true</tt> if oldPassword is valid
     * @see User
     */
    boolean isValidOldPasssword(User loggedUser, String oldPassword);
}
