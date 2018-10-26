package com.labproject.restaurant.dao;

import com.labproject.restaurant.entities.Bill;

import java.util.List;

/**
 * Data Access Object interface for the {@link Bill} objects.
 * Provides CRUD operations with {@link Bill} objects.
 *
 * @author Zhanna Fedorova
 */
public interface BillDao {

    /**
     * Returns a {@link Bill} object from a data storage
     * by the specified id
     *
     * @param id a primary key of Bill table
     * @return the Bill object at the specified id
     * @see Bill
     */
    Bill getById(long id);

    /**
     * Returns a List of {@link Bill} objects from a data storage
     * by the specified column
     *
     * @return the list of Bill objects
     * @see Bill
     */
    List<Bill> getByAdminId(long adminId);

    /**
     * Inserts the {@link Bill} object into a data storage
     *
     * @param bill the Bill entity
     * @see Bill
     */
    void insert(Bill bill);

    /**
     * Updates the {@link Bill} object in a data storage
     *
     * @param bill the Bill entity
     * @see Bill
     */
    void update(Bill bill);

    /**
     * Deletes the {@link Bill} object from data storage
     * by a specified id
     *
     * @param id a primary key of Bill table
     * @see Bill
     */
    void deleteById(long id);
}
