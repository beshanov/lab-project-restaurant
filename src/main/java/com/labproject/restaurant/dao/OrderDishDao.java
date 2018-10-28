package com.labproject.restaurant.dao;

import com.labproject.restaurant.entities.Dish;
import com.labproject.restaurant.entities.Order;

import java.util.List;

public interface OrderDishDao {

    void addDishToOrder(Dish dish, Order order, int count);

    List<Dish> getDishesByOrder(Order order);

    void deleteDishFromOrder(Dish dish, Order order);
}
