package com.labproject.restaurant.dao.impl;

import com.labproject.restaurant.dao.OrderDao;
import com.labproject.restaurant.entities.Order;
import com.labproject.restaurant.entities.OrderStatus;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insert(Order order) {
        String query = "INSERT INTO `ORDER` (ORDERDATE, USERID, STATUSID) VALUES (?, ?, ?);";
        PreparedStatement ps;

        try (Connection connection = dataSource.getConnection()) {
            ps = connection.prepareStatement(query);
            ps.setTimestamp(1, order.getOrderDate());
            ps.setLong(2, order.getUser().getId());
            ps.setLong(3, order.getStatus().getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            // TODO: exception handling
        }
    }

    @Override
    public Order getById(long orderId) {
        Order result = new Order();
        String query = "SELECT * FROM `ORDER` WHERE ID = ?;";
        PreparedStatement ps;

        try (Connection connection = dataSource.getConnection()) {
            ps = connection.prepareStatement(query);
            ps.setLong(1, orderId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result.setId(rs.getLong("ID"));
                result.setOrderDate(rs.getTimestamp("ORDERDATE"));
                result.setUser(new User());
                result.getUser().setId(rs.getLong("USERID"))
                result.setStatus(new OrderStatus());
                result.getStatus().setId(rs.getLong("STATUSID"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            // TODO: exception handling
            return new Order();
        }

        return result;
    }

    @Override
    public List<Order> getAll() {
        List<Order> result = new ArrayList<>();
        String query = "SELECT * FROM `ORDER`;";
        Order tmpOrder;
        PreparedStatement ps;

        try (Connection connection = dataSource.getConnection()) {
            ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tmpOrder = new Order();
                tmpOrder.setId(rs.getLong("ID"));
                tmpOrder.setOrderDate(rs.getTimestamp("ORDERDATE"));
                tmpOrder.setUser(new User());
                tmpOrder.getUser().setId(rs.getLong("USERID"));
                tmpOrder.setStatus(new OrderStatus());
                tmpOrder.getStatus().setId(rs.getLong("STATUSID"));
                result.add(tmpOrder);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            // TODO: exception handling
            return new ArrayList<>();
        }

        return result;
    }

    @Override
    public void update(Order order) {
        String query = "UPDATE `ORDER` SET ORDERDATE = ?, USERID = ?, STATUSID = ? WHERE ID = ?;";
        PreparedStatement ps;

        try (Connection connection = dataSource.getConnection()) {
            ps = connection.prepareStatement(query);
            ps.setTimestamp(1, order.getOrderDate());
            ps.setLong(2, order.getUser().getId());
            ps.setLong(3, order.getStatus().getId());
            ps.setLong(4, order.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            // TODO: exception handling
        }
    }

    @Override
    public void deleteById(long orderId) {
        String query = "DELETE FROM `ORDER` WHERE ID = ?";
        PreparedStatement ps;

        try (Connection connection = dataSource.getConnection()) {
            ps = connection.prepareStatement(query);
            ps.setLong(1, orderId);
            ps.close();
        } catch (SQLException e) {
            // TODO: exception handling
        }
    }
}
