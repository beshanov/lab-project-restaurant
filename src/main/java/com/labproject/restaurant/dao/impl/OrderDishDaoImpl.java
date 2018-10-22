package com.labproject.restaurant.dao.impl;

import com.labproject.restaurant.dao.OrderDishDao;
import com.labproject.restaurant.entities.DishEntity;
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
    public void addDishToOrder(DishEntity dishEntity, Order order, int count) {
        final String ADD_DISH_TO_ORDER = "INSERT INTO order_dish(dishid, orderid, count) VALUES(?, ?, ?);";
        try (Connection conn = dataSource.getConnection(); PreparedStatement statement = conn.prepareStatement(ADD_DISH_TO_ORDER)) {
            statement.setLong(1, dishEntity.getId());
            statement.setLong(2, order.getId());
            statement.setDouble(3, count);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteDishFromOrder(DishEntity dishEntity, Order order) {
        final String DELETE_DISH_FROM_ORDER = "DELETE FROM order_dish WHERE dishid = ? AND orderid = ?;";
        try (Connection conn = dataSource.getConnection(); PreparedStatement statement = conn.prepareStatement(DELETE_DISH_FROM_ORDER)) {
            statement.setLong(1, dishEntity.getId());
            statement.setLong(2, order.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error: " + e.getMessage(), e);
        }
    }
}
