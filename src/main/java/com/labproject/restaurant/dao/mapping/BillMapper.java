package com.labproject.restaurant.dao.mapping;

import com.labproject.restaurant.entities.Bill;
import com.labproject.restaurant.entities.Order;
import com.labproject.restaurant.entities.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BillMapper implements RowMapper<Bill> {
    @Override
    public Bill mapRow(ResultSet rs, int rowNum) throws SQLException {
        Bill bill = new Bill();
        bill.setId(rs.getLong("ID"));
        Order order = new Order();
        order.setId(rs.getLong("ORDER_ID"));
        bill.setOrder(order);
        User admin = new User();
        admin.setId(rs.getLong("ADMIN_ID"));
        bill.setAdmin(admin);
        bill.setAmount(rs.getBigDecimal("AMOUNT"));
        bill.setDate(rs.getTimestamp("DATE"));
        bill.setStatus(rs.getBoolean("STATUS"));
        return bill;
    }
}
