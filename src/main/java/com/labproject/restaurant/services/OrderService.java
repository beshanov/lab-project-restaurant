package com.labproject.restaurant.services;

import com.labproject.restaurant.entities.Dish;
import com.labproject.restaurant.entities.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {

    void createNewOrder(Order order);

    Order getOrderById(long orderId);

    void createOrderWithDishes(long userId, Map<Dish, Integer> dishMap);

    List<Order> getAllOrders();

    void setOrder(Order order);

    void deleteOrderById(long orderId);
}
