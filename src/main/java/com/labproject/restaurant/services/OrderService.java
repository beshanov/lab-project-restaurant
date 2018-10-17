package com.labproject.restaurant.services;

import com.labproject.restaurant.entities.Order;

import java.util.List;

public interface OrderService {

    void createNewOrder(Order order);

    Order getOrderById(long orderId);

    List<Order> getAllOrders();

    List<Order> getOrdersByUserId(long userId);

    List<Order> getOrdersByStatusId(long statusId);

    void setOrder(Order order);

    void deleteOrderById(long orderId);
}
