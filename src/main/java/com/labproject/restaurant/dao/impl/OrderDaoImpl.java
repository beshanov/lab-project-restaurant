package com.labproject.restaurant.dao.impl;

import com.labproject.restaurant.dao.OrderDao;
import com.labproject.restaurant.entities.Order;
import com.labproject.restaurant.entities.OrderStatus;
import com.labproject.restaurant.entities.User;
import org.apache.commons.dbutils.DbUtils;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private final Logger logger = Logger.getLogger(OrderDaoImpl.class);
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insert(Order order) {
        String query = "INSERT INTO `ORDER` (ORDERDATE, USERID, STATUSID) VALUES (?, ?, ?)";
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement(query);
            ps.setTimestamp(1, order.getOrderDate());
            ps.setLong(2, order.getUser().getId());
            ps.setLong(3, order.getStatus().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error: " + e.getMessage(), e);
        } finally {
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(connection);
        }
    }

    @Override
    public Order getById(long orderId) {
        Order result = new Order();
        String query = "SELECT * FROM `ORDER` WHERE ID = ?";
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement(query);
            ps.setLong(1, orderId);
            rs = ps.executeQuery();
            if (rs.next()) {
                result.setId(rs.getLong("ID"));
                result.setOrderDate(rs.getTimestamp("ORDERDATE"));
                result.setUser(new User());
                result.getUser().setId(rs.getLong("USERID"));
                result.setStatus(new OrderStatus());
                result.getStatus().setId(rs.getLong("STATUSID"));
            }
        } catch (SQLException e) {
            logger.error("Error: " + e.getMessage(), e);
            return new Order();
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }

        return result;
    }

    @Override
    public List<Order> getAll() {
        List<Order> result = new ArrayList<>();
        String query = "SELECT * FROM `ORDER`";
        Order tmpOrder;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
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
        } catch (SQLException e) {
            logger.error("Error: " + e.getMessage(), e);
            return new ArrayList<>();
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }

        return result;
    }

    @Override
    public void update(Order order) {
        String query = "UPDATE `ORDER` SET ORDERDATE = ?, USERID = ?, STATUSID = ? WHERE ID = ?";
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement(query);
            ps.setTimestamp(1, order.getOrderDate());
            ps.setLong(2, order.getUser().getId());
            ps.setLong(3, order.getStatus().getId());
            ps.setLong(4, order.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error: " + e.getMessage(), e);
        } finally {
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(connection);
        }
    }

    @Override
    public void deleteById(long orderId) {
        String query = "DELETE FROM `ORDER` WHERE ID = ?";
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement(query);
            ps.setLong(1, orderId);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error: " + e.getMessage(), e);
        } finally {
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(connection);
        }
    }
}
