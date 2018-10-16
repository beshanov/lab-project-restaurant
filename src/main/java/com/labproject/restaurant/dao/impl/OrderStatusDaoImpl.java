package com.labproject.restaurant.dao.impl;

import com.labproject.restaurant.dao.OrderStatusDao;
import com.labproject.restaurant.entities.OrderStatus;
import org.apache.commons.dbutils.DbUtils;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderStatusDaoImpl implements OrderStatusDao {
    private final Logger logger = Logger.getLogger(OrderStatusDaoImpl.class);
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public OrderStatus getById(long orderStatusId) {
        OrderStatus result = new OrderStatus();
        String query = "SELECT * FROM ORDER_STATUS WHERE ID = ?";
        PreparedStatement ps = null;
        Connection connection = null;
        ResultSet rs = null;

        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement(query);
            ps.setLong(1, orderStatusId);
            rs = ps.executeQuery();
            if (rs.next()) {
                result.setId(rs.getLong("ID"));
                result.setName(rs.getString("NAME"));
            }
        } catch (SQLException e) {
            logger.error("Error: " + e.getMessage(), e);
            return new OrderStatus();
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }

        return result;
    }

    @Override
    public List<OrderStatus> getAll() {
        List<OrderStatus> result = new ArrayList<>();
        String query = "SELECT * FROM ORDER_STATUS";
        OrderStatus tmpOrderStatus;
        PreparedStatement ps = null;
        Connection connection = null;
        ResultSet rs = null;

        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                tmpOrderStatus = new OrderStatus();
                tmpOrderStatus.setId(rs.getLong("ID"));
                tmpOrderStatus.setName(rs.getString("NAME"));
                result.add(tmpOrderStatus);
            }
        } catch (SQLException e) {
            logger.error("Error: " + e.getMessage(), e);
            return new ArrayList<>();
        } finally {
            DbUtils.closeQuietly(connection, ps, rs);
        }

        return result;
    }
}
