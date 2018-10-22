package com.labproject.restaurant.dao.impl;

import com.labproject.restaurant.dao.OrderDao;
import com.labproject.restaurant.entities.Order;
import com.labproject.restaurant.entities.OrderStatus;
import com.labproject.restaurant.entities.User;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private static final Logger LOGGER = Logger.getLogger(OrderDaoImpl.class);
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insert(Order order) {
        String query = "INSERT INTO `ORDER` (ORDERDATE, USER_ID, STATUS_ID) VALUES (?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setTimestamp(1, order.getOrderDate());
            ps.setLong(2, order.getUser().getId());
            ps.setLong(3, order.getStatus().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error: " + e.getMessage(), e);
        }
    }

    @Override
    public Order getById(long orderId) {
        Order result = new Order();
        String query = "SELECT * FROM `ORDER` WHERE ID = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, orderId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result.setId(rs.getLong("ID"));
                result.setOrderDate(rs.getTimestamp("ORDERDATE"));
                result.setUser(new User());
                result.getUser().setId(rs.getLong("USER_ID"));
                result.setStatus(new OrderStatus());
                result.getStatus().setId(rs.getLong("STATUS_ID"));
            }
        } catch (SQLException e) {
            LOGGER.error("Error: " + e.getMessage(), e);
            return new Order();
        }

        return result;
    }

    @Override
    public List<Order> getAll() {
        List<Order> result = new ArrayList<>();
        String query = "SELECT * FROM `ORDER`";
        Order tmpOrder;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tmpOrder = new Order();
                tmpOrder.setId(rs.getLong("ID"));
                tmpOrder.setOrderDate(rs.getTimestamp("ORDERDATE"));
                tmpOrder.setUser(new User());
                tmpOrder.getUser().setId(rs.getLong("USER_ID"));
                tmpOrder.setStatus(new OrderStatus());
                tmpOrder.getStatus().setId(rs.getLong("STATUS_ID"));
                result.add(tmpOrder);
            }
        } catch (SQLException e) {
            LOGGER.error("Error: " + e.getMessage(), e);
            return new ArrayList<>();
        }

        return result;
    }

    @Override
    public void update(Order order) {
        String query = "UPDATE `ORDER` SET ORDERDATE = ?, USER_ID = ?, STATUS_ID = ? WHERE ID = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setTimestamp(1, order.getOrderDate());
            ps.setLong(2, order.getUser().getId());
            ps.setLong(3, order.getStatus().getId());
            ps.setLong(4, order.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteById(long orderId) {
        String query = "DELETE FROM `ORDER` WHERE ID = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, orderId);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error: " + e.getMessage(), e);
        }
    }
}
