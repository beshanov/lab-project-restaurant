package com.labproject.restaurant.dao.impl;

import com.labproject.restaurant.dao.OrderDishDao;
import com.labproject.restaurant.entities.Dish;
import com.labproject.restaurant.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class OrderDishDaoImpl implements OrderDishDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addDishToOrder(Dish dish, Order order, int count) {
        final String ADD_DISH_TO_ORDER = "INSERT INTO ORDER_DISH(DISH_ID, ORDER_ID, COUNT) VALUES(?, ?, ?)";
        jdbcTemplate.update(ADD_DISH_TO_ORDER, dish.getId(), order.getId(), count);
    }

    @Override
    public void deleteDishFromOrder(Dish dish, Order order) {
        final String DELETE_DISH_FROM_ORDER = "DELETE FROM ORDER_DISH WHERE DISH_ID = ? AND ORDER_ID = ?";
        jdbcTemplate.update(DELETE_DISH_FROM_ORDER, dish.getId(), order.getId());
    }

    @Override
    public void deleteDish(Dish dish) {
        final String query = "DELETE FROM ORDER_DISH WHERE DISH_ID = ?";
        jdbcTemplate.update(query, dish.getId());
    }
}