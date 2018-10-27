package com.labproject.restaurant.dao.impl;

import com.labproject.restaurant.dao.OrderDishDao;
import com.labproject.restaurant.entities.Dish;
import com.labproject.restaurant.entities.Order;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDishDaoImpl implements OrderDishDao {

    private DataSource dataSource;
    private static final Logger LOGGER = Logger.getLogger(OrderDishDaoImpl.class);

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addDishToOrder(Dish dish, Order order, int count) {
        final String ADD_DISH_TO_ORDER = "INSERT INTO ORDER_DISH(DISH_ID, ORDER_ID, COUNT) VALUES(?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(ADD_DISH_TO_ORDER)) {
            statement.setLong(1, dish.getId());
            statement.setLong(2, order.getId());
            statement.setInt(3, count);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteDishFromOrder(Dish dish, Order order) {
        final String DELETE_DISH_FROM_ORDER = "DELETE FROM ORDER_DISH WHERE DISH_ID = ? AND ORDER_ID = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(DELETE_DISH_FROM_ORDER)) {
            statement.setLong(1, dish.getId());
            statement.setLong(2, order.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error: " + e.getMessage(), e);
        }
    }
}
