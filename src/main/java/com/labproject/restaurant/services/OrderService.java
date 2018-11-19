package com.labproject.restaurant.services;

import com.labproject.restaurant.entities.Dish;
import com.labproject.restaurant.entities.Order;

import java.util.List;
import java.util.Map;

/**
 * A service layer interface that provides the CRUD functionality
 * for the {@link Order} entity
 *
 * @author Igor Pevtsov
 * @author Alexandr Zorin
 * @author Vsevolod Beshanov
 * @author Zhanna Fedorova
 * @see Order
 */
public interface OrderService {

    /**
     * Inserts the {@link Order} object into a data storage
     *
     * @param order the Order entity
     * @see Order
     */
    void createNewOrder(Order order);

    /**
     * Inserts the {@link Order} and {@link Dish} objects
     * into a data storage
     *
     * @param dishMap dishes to insert
     * @see Order
     * @see Dish
     */
    void createOrderWithDishes(Map<Dish, Integer> dishMap);

    /**
     * Returns an {@link Order} object
     * with specified id
     *
     * @param orderId the id of Order
     * @return an {@link Order} object with specified id
     * @see Order
     */
    Order getOrderById(long orderId);

    /**
     * Returns a logged user List of {@link Order} objects from a data storage
     *
     * @return the list of Order objects
     * @see Order
     */
    List<Order> getOrdersByUser();

    /**
     * Updates the {@link Order} object in a data storage
     *
     * @param order the Order entity
     * @see Order
     */
    void setOrder(Order order);

    /**
     * Deletes the {@link Order} object from data storage
     * by a specified id
     *
     * @param orderId a primary key of Order table
     * @see Order
     */
    void deleteOrderById(long orderId);

    /**
     * Updates the status of {@link Order} object in a data storage
     * with specified orderId to statusId
     *
     * @param orderId
     * @param statusId
     */
    void setOrderStatus(long orderId, long statusId);
}
