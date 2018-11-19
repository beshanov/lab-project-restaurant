package com.labproject.restaurant.dao;

import com.labproject.restaurant.entities.User;

import java.util.List;

/**
 * Data Access Object interface for the {@link User} objects.
 * Provides CRUD operations with {@link User} objects.
 *
 * @author Igor Pevtsov
 * @author Alexandr Zorin
 * @author Vsevolod Beshanov
 * @author Zhanna Fedorova
 */
public interface UserDao {

    /**
     * Returns a {@link User} object from a data storage
     * by the specified id
     *
     * @param id a primary key of User table
     * @return the User object at the specified id
     * @see User
     */
    User getById(long id);

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
     * @param user the User entity
     * @see User
     */
    void updateRole(User user);

    /**
     * Updates password field of the {@link User} object in a data storage
     *
     * @param user the User entity
     * @see User
     */
    void updatePassword(User user);

    /**
     * Deletes the {@link User} object from data storage
     * by a specified id
     *
     * @param user the User entity
     * @see User
     */
    void delete(User user);

    /**
     * Returns a List of {@link User} objects from a data storage
     *
     * @return the list of User objects
     * @see User
     */
    List<User> getAllUsers(boolean full, long userId);
}
