package com.labproject.restaurant.dao;

import com.labproject.restaurant.entities.OrderStatus;

import java.util.List;

public interface OrderStatusDao {

    OrderStatus getById(long orderStatusId);

    List<OrderStatus> getAll();
}
