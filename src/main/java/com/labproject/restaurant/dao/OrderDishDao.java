package com.labproject.restaurant.dao;

import com.labproject.restaurant.entities.DishEntity;
import com.labproject.restaurant.entities.Order;

import java.util.List;

public interface OrderDishDao {
    void addDishToOrder(DishEntity dishEntity, Order order, int count);
    void deleteDishFromOrder(DishEntity dishEntity, Order order);
    List<DishEntity> getAllDishFromOrder(Order order);
}