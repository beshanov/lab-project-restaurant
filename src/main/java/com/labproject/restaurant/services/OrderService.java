package com.labproject.restaurant.services;

import com.labproject.restaurant.entities.Dish;
import com.labproject.restaurant.entities.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {

    void createNewOrder(Order order);

    void createOrderWithDishes(Object objUser, Object objDishMap);

    Order getOrderById(long orderId);

    Map<Order, List<Dish>> getAllOrdersWithDishes();

    Map<Order, List<Dish>> getOrdersWithDishesByUser(Object objUser);

    void setOrder(Order order);

    void deleteOrderById(long orderId);
}
