package com.labproject.restaurant.dao;

import com.labproject.restaurant.entities.Dish;
import com.labproject.restaurant.entities.Order;

public interface OrderDishDao {
    void addDishToOrder(Dish dish, Order order, int count);

    void deleteDishFromOrder(Dish dish, Order order);

    void deleteDish(Dish dish);
}
