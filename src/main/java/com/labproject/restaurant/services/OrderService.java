package com.labproject.restaurant.services;

import com.labproject.restaurant.entities.Dish;
import com.labproject.restaurant.entities.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {

    void createNewOrder(Order order);

    void createOrderWithDishes(Map<Dish, Integer> dishMap);

    Order getOrderById(long orderId);

    List<Order> getOrdersByUser();

    void setOrder(Order order);

    void deleteOrderById(long orderId);
}
