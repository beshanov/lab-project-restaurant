package com.labproject.restaurant.dao.mapping;

import com.labproject.restaurant.entities.Order;
import com.labproject.restaurant.entities.OrderStatus;
import com.labproject.restaurant.entities.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setId(rs.getLong("ID"));
        order.setOrderDate(rs.getTimestamp("ORDERDATE"));

        User user = new User();
        user.setId(rs.getLong("USER_ID"));
        order.setUser(user);

        OrderStatus status = new OrderStatus();
        status.setId(rs.getLong("STATUS_ID"));
        order.setStatus(status);

        User admin = new User();
        admin.setId(rs.getLong("ADMIN_ID"));
        order.setAdmin(admin);

        order.setAmount(rs.getBigDecimal("AMOUNT"));

        return order;
    }
}
