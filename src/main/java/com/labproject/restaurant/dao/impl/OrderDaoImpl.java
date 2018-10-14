package com.labproject.restaurant.dao.impl;

import com.labproject.restaurant.dao.OrderDao;
import com.labproject.restaurant.entities.OrderEntity;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class OrderDaoImpl implements OrderDao {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(OrderEntity orderEntity) {
        String request = "INSERT INTO `ORDER` (ORDERDATE, USERID, STATUSID) VALUES (?, ?, ?);";
        PreparedStatement ps;

        try {
            ps = dataSource.getConnection().prepareStatement(request);
            ps.setTimestamp(1, orderEntity.getOrderDate());
            ps.setLong(2, orderEntity.getUserId());
            ps.setLong(3, orderEntity.getStatusId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            // TODO: exception handling
        }
    }

    @Override
    public OrderEntity getById(long orderId) {
        OrderEntity result = new OrderEntity(0, new Timestamp(0L), 0, 0);

        if (orderId < 1) {
            return result;
        }

        String request = "SELECT * FROM `ORDER` WHERE ID = ?;";
        PreparedStatement ps;

        try {
            ps = dataSource.getConnection().prepareStatement(request);
            ps.setLong(1, orderId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = new OrderEntity(
                        rs.getLong(1),
                        rs.getTimestamp(2),
                        rs.getLong(3),
                        rs.getLong(4));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            // TODO: exception handling
        }

        return result;
    }

    @Override
    public Set<OrderEntity> getAll() {
        String request = "SELECT * FROM `ORDER`;";
        Set<OrderEntity> result = new HashSet<>();
        OrderEntity tmpOrderEntity;
        PreparedStatement ps;

        try {
            ps = dataSource.getConnection().prepareStatement(request);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tmpOrderEntity = new OrderEntity(
                        rs.getLong(1),
                        rs.getTimestamp(2),
                        rs.getLong(3),
                        rs.getLong(4));
                result.add(tmpOrderEntity);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            // TODO: exception handling
        }

        return result;
    }

    @Override
    public void set(OrderEntity orderEntity) {
        if (orderEntity == null) {
            return;
        }

        String request = "UPDATE `ORDER` SET ORDERDATE = ?, USERID = ?, STATUSID = ? WHERE ID = ?;";
        PreparedStatement ps;

        try {
            ps = dataSource.getConnection().prepareStatement(request);
            ps.setTimestamp(1, orderEntity.getOrderDate());
            ps.setLong(2, orderEntity.getUserId());
            ps.setLong(3, orderEntity.getStatusId());
            ps.setLong(4, orderEntity.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            // TODO: exception handling
        }
    }

    @Override
    public void deleteById(long orderId) {
        if (orderId < 1) {
            return;
        }

        String request = "DELETE FROM `ORDER` WHERE ID = ?";
        PreparedStatement ps;

        try {
            ps = dataSource.getConnection().prepareStatement(request);
            ps.setLong(1, orderId);
            ps.close();
        } catch (SQLException e) {
            // TODO: exception handling
        }
    }
}
