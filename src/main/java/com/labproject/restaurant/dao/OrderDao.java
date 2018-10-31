package com.labproject.restaurant.dao;

import com.labproject.restaurant.entities.Order;

import java.util.List;

public interface OrderDao {

    void insert(Order order);

    Order getById(long orderId);

    List<Order> getAll();

    List<Order> getAllByUserId(long userId);

    void update(Order order);

    void deleteById(long orderId);
}
