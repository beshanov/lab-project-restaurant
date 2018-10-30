package com.labproject.restaurant.services;

import com.labproject.restaurant.entities.Order;

import java.util.List;

public interface OrderService {

    void createNewOrder(Order order);

    void createOrderWithDishes(Object objDishMap);

    Order getOrderById(long orderId);

    List<Order> getOrdersByUser(Object objUser);

    void setOrder(Order order);

    void deleteOrderById(long orderId);
}
