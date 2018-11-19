package com.labproject.restaurant.services;

import com.labproject.restaurant.entities.Role;

import java.util.List;

/**
 * A service layer interface that provides the CRUD functionality
 * for the {@link Role} entity
 *
 * @author Igor Pevtsov
 * @author Alexandr Zorin
 * @author Vsevolod Beshanov
 * @author Zhanna Fedorova
 * @see Role
 */
public interface RoleService {

    /**
     * Returns a {@link Role} object from a data storage
     * by the specified id
     *
     * @param id a primary key of Role table
     * @return the Role object at the specified id
     * @see Role
     */
    Role getById(long id);

    /**
     * Inserts the {@link Role} object into a data storage
     *
     * @param role the Role entity
     * @see Role
     */
    void insert(Role role);

    /**
     * Updates the {@link Role} object in a data storage
     *
     * @param role the Role entity
     * @see Role
     */
    void update(Role role);

    /**
     * Deletes the {@link Role} object from data storage
     * by a specified id
     *
     * @param role the Role entity
     * @see Role
     */
    void delete(Role role);

    /**
     * Returns a {@link Role} object from a data storage
     * by the specified login of {@link Role}
     *
     * @param login a unique identificator of User
     * @return the Role object at the specified login
     * @see Role
     */
    Role getRoleByLogin(String login);

    /**
     * Returns a List of {@link Role} objects from a data storage
     *
     * @return the list of Role objects
     * @see Role
     */
    List<Role> getAllRoles();
}
