package com.labproject.restaurant.dao;

import com.labproject.restaurant.entities.DishEntity;
import com.labproject.restaurant.entities.Order;

public interface OrderDishDao {
    void addDishToOrder(DishEntity dishEntity, Order order, int count);
    void deleteDishFromOrder(DishEntity dishEntity, Order order);
}
