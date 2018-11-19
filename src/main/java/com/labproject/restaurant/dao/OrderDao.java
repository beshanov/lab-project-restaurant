package com.labproject.restaurant.dao;

import com.labproject.restaurant.entities.Order;

import java.util.List;

/**
 * Data Access Object interface for the {@link Order} objects.
 * Provides CRUD operations with {@link Order} objects.
 *
 * @author Igor Pevtsov
 * @author Alexandr Zorin
 * @author Vsevolod Beshanov
 * @author Zhanna Fedorova
 */
public interface OrderDao {

    /**
     * Inserts the {@link Order} object into a data storage
     *
     * @param order the Order entity
     * @see Order
     */
    void insert(Order order);

    /**
     * Returns a {@link Order} object from a data storage
     * by the specified id
     *
     * @param orderId a primary key of Order table
     * @return the Order object at the specified id
     * @see Order
     */
    Order getById(long orderId);

    /**
     * Returns a List of {@link Order} objects from a data storage
     *
     * @return the list of Order objects
     * @see Order
     */
    List<Order> getAll();

    /**
     * Returns a List of {@link Order} objects from a data storage
     * by specified userId
     *
     * @param userId a primary key of User table
     * @return the list of Order objects
     * @see Order
     */
    List<Order> getAllByUserId(long userId);

    /**
     * Updates the {@link Order} object in a data storage
     *
     * @param order the Order entity
     * @see Order
     */
    void update(Order order);

    /**
     * Deletes the {@link Order} object from data storage
     * by a specified id
     *
     * @param orderId a primary key of Order table
     * @see Order
     */
    void deleteById(long orderId);
}
