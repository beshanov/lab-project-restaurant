package com.labproject.restaurant.dao;

import com.labproject.restaurant.entities.OrderStatusEntity;

import java.util.Set;

public interface OrderStatusDao {

    public OrderStatusEntity getById(long orderStatusId);

    public Set<OrderStatusEntity> getAll();
}
