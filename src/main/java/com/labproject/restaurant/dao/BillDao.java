package com.labproject.restaurant.dao;

import com.labproject.restaurant.entities.Bill;

import java.util.List;

/**
 * This is a DAO interface for Bill entity
 */
public interface BillDao {

    /**
     * Returns a Bill object from Bill database table
     * by the specified id
     *
     * @param id a primary key of Bill table
     * @return the Bill object at the specified id
     * @see Bill
     */
    Bill getById(long id);

    /**
     * Returns a List of Bill objects from Bill database table
     *
     * @return the list of Bill objects
     * @see Bill
     */
    List<Bill> getAll();

    /**
     * Inserts the line in Bill database table.
     *
     * @param bill the Bill entity
     * @see Bill
     */
    void insert(Bill bill);

    /**
     * Changes the line in Bill database table with id specified
     * in the parameter bill
     *
     * @param bill the Bill entity
     * @see Bill
     */
    void update(Bill bill);

    /**
     * Deletes a line from "Bill" database table
     * by a specified id
     *
     * @param id a primary key of Bill table
     * @see Bill
     */
    void deleteById(long id);
}
