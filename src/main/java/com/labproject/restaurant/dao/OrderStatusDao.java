package com.labproject.restaurant.dao;

import com.labproject.restaurant.entities.OrderStatus;

import java.util.List;

public interface OrderStatusDao {

    public OrderStatus getById(long orderStatusId);

    public List<OrderStatus> getAll();
}
