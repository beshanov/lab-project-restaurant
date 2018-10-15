package com.labproject.restaurant.dao;

import com.labproject.restaurant.entities.Order;

import java.util.List;

public interface OrderDao {

    public void insert(Order order);

    public Order getById(long orderId);

    public List<Order> getAll();

    public void update(Order order);

    public void deleteById(long orderId);
}
