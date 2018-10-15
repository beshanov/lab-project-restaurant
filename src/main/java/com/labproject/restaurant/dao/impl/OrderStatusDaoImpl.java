package com.labproject.restaurant.dao.impl;

import com.labproject.restaurant.dao.OrderStatusDao;
import com.labproject.restaurant.entities.OrderStatus;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderStatusDaoImpl implements OrderStatusDao {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public OrderStatus getById(long orderStatusId) {
        OrderStatus result = new OrderStatus();
        String query = "SELECT * FROM ORDER_STATUS WHERE ID = ?;";
        PreparedStatement ps;

        try (Connection connection = dataSource.getConnection()) {
            ps = connection.prepareStatement(query);
            ps.setLong(1, orderStatusId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result.setId(rs.getLong("ID"));
                result.setName(rs.getString("NAME"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            // TODO: exception handling
            return new OrderStatus();
        }

        return result;
    }

    @Override
    public List<OrderStatus> getAll() {
        List<OrderStatus> result = new ArrayList<>();
        String query = "SELECT * FROM ORDER_STATUS;";
        PreparedStatement ps;
        OrderStatus tmpOrderStatus;

        try (Connection connection = dataSource.getConnection() {
            ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tmpOrderStatus = new OrderStatus();
                tmpOrderStatus.setId(rs.getLong("ID"));
                tmpOrderStatus.setName(rs.getString("NAME"));
                result.add(tmpOrderStatus);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            // TODO: exception handling
            return new ArrayList<>();
        }

        return result;
    }
}
