package com.labproject.restaurant.dao.impl;

import com.labproject.restaurant.dao.OrderStatusDao;
import com.labproject.restaurant.entities.OrderStatusEntity;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class OrderStatusDaoImpl implements OrderStatusDao {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public OrderStatusEntity getById(long orderStatusId) {
        OrderStatusEntity result = new OrderStatusEntity(0, "");

        if (orderStatusId < 1) {
            return result;
        }

        String request = "SELECT * FROM ORDER_STATUS WHERE ID = ?;";
        PreparedStatement ps;

        try {
            ps = dataSource.getConnection().prepareStatement(request);
            ps.setLong(1, orderStatusId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = new OrderStatusEntity(
                        rs.getLong(1),
                        rs.getString(2));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            // TODO: exception handling
        }

        return result;
    }

    @Override
    public Set<OrderStatusEntity> getAll() {
        String request = "SELECT * FROM ORDER_STATUS;";
        Set<OrderStatusEntity> result = new HashSet<>();
        PreparedStatement ps;

        try {
            ps = dataSource.getConnection().prepareStatement(request);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new OrderStatusEntity(
                        rs.getLong(1),
                        rs.getString(2)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            // TODO: exception handling
        }

        return result;
    }
}
