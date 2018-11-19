package com.labproject.restaurant.dao;

import com.labproject.restaurant.entities.Dish;
import com.labproject.restaurant.entities.Order;

import java.util.Map;

/**
 * Data Access Object interface.
 * Provides CRUD operations with {@link Dish} objects.
 *
 * @author Igor Pevtsov
 * @author Alexandr Zorin
 * @author Vsevolod Beshanov
 * @author Zhanna Fedorova
 */
public interface OrderDishDao {

    /**
     * Inserts the set of {@link Dish} object into a data storage
     *
     * @param dish  the Dish entity
     * @param order the Order entity
     * @param count the number of dish objects
     * @see Dish, Order
     */
    void addDishToOrder(Dish dish, Order order, int count);

    /**
     * Returns a Map of {@link Dish} objects with their
     * count from a data storage by specified orderId
     *
     * @param orderId a primary key of Role table
     * @return the Role object at the specified id
     * @see Dish
     */
    Map<Dish, Integer> getDishesByOrderId(long orderId);

    void deleteDishFromOrder(Dish dish, Order order);

    void deleteDish(Dish dish);
}
