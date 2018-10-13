package com.labproject.restaurant.dao;

import com.labproject.restaurant.entities.OrderEntity;

import java.util.Set;

public interface OrderDao {

    public void add(OrderEntity orderEntity);

    public OrderEntity getById(long orderId);

    public Set<OrderEntity> getAll();

    public void set(OrderEntity orderEntity);

    public void deleteById(long orderId);
}
