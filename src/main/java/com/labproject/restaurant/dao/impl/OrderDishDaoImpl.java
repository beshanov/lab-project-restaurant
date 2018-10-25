package com.labproject.restaurant.dao.impl;

import com.labproject.restaurant.dao.OrderDishDao;
import com.labproject.restaurant.entities.Dish;
import com.labproject.restaurant.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class OrderDishDaoImpl implements OrderDishDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void addDishToOrder(Dish dish, Order order, int count) {
        final String ADD_DISH_TO_ORDER = "INSERT INTO ORDER_DISH(DISHID, ORDERID, COUNT) VALUES(?, ?, ?)";
        jdbcTemplate.update(ADD_DISH_TO_ORDER, dish.getId(), order.getId(), count);
    }

    @Override
    public void deleteDishFromOrder(Dish dish, Order order) {
        final String DELETE_DISH_FROM_ORDER = "DELETE FROM ORDER_DISH WHERE DISHID = ? AND ORDERID = ?";
        jdbcTemplate.update(DELETE_DISH_FROM_ORDER, dish.getId(), order.getId());
    }
}