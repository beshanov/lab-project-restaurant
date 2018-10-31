package com.labproject.restaurant.dao;

import com.labproject.restaurant.entities.Dish;
import com.labproject.restaurant.entities.Order;

import java.util.Map;

public interface OrderDishDao {

    void addDishToOrder(Dish dish, Order order, int count);

    Map<Dish, Integer> getDishesByOrderId(long orderId);

    void deleteDishFromOrder(Dish dish, Order order);
}
