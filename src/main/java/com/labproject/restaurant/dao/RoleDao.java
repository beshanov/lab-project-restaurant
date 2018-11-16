package com.labproject.restaurant.dao;

import com.labproject.restaurant.entities.Role;

import java.util.List;

/**
 * Data Access Object interface for the {@link Role} objects.
 * Provides CRUD operations with {@link Role} objects.
 *
 * @author Zhanna Fedorova
 */
public interface RoleDao {

    /**
     * Returns a {@link Role} object from a data storage
     * by the specified id
     *
     * @param id a primary key of Role table
     * @return the Role object at the specified id
     * @see {@link Role}
     */
    Role getById(long id);

    /**
     * Returns a {@link Role} object from a data storage
     * by the specified login of {@link Role}
     *
     * @param login a primary key of Role table
     * @return the Role object at the specified id
     * @see {@link Role}
     */
    Role getRoleByLogin(String login);

    /**
     * Inserts the {@link Role} object into a data storage
     *
     * @param role the Role entity
     * @see {@link Role}
     */
    void insert(Role role);

    /**
     * Updates the {@link Role} object in a data storage
     *
     * @param role the Role entity
     * @see {@link Role}
     */
    void update(Role role);

    /**
     * Deletes the {@link Role} object from data storage
     * by a specified id
     *
     * @param role the Role entity
     * @see {@link Role}
     */
    void delete(Role role);

    /**
     * Returns a List of {@link Role} objects from a data storage
     *
     * @return the list of Role objects
     * @see {@link Role}
     */
    List<Role> getAllRoles();
}
