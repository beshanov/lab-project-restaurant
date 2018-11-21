package com.labproject.restaurant.dao;

import com.labproject.restaurant.entities.OrderStatus;

import java.util.List;

/**
 * Data Access Object interface for the {@link OrderStatus} objects.
 * Provides CRUD operations with {@link OrderStatus} objects.
 *
 * @author Igor Pevtsov
 * @author Alexandr Zorin
 * @author Vsevolod Beshanov
 * @author Zhanna Fedorova
 */
public interface OrderStatusDao {

    /**
     * Returns a {@link OrderStatus} object from a data storage
     * by the specified id
     *
     * @param orderStatusId a primary key of OrderStatus table
     * @return the OrderStatus object at the specified id
     * @see OrderStatus
     */
    OrderStatus getById(long orderStatusId);

    /**
     * Returns a List of {@link OrderStatus} objects from a data storage
     *
     * @return the list of OrderStatus objects
     * @see OrderStatus
     */
    List<OrderStatus> getAll();
}
