package com.labproject.restaurant.dao.mapping;

import com.labproject.restaurant.entities.Order;
import com.labproject.restaurant.entities.OrderStatus;
import com.labproject.restaurant.entities.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * An implementation of a {@link RowMapper} interface
 * that performs the actual work of mapping each row to a {@link Order} object
 *
 * @author Igor Pevtsov
 * @author Alexandr Zorin
 * @author Vsevolod Beshanov
 * @author Zhanna Fedorova
 * @see RowMapper
 * @see Order
 */
public class OrderMapper implements RowMapper<Order> {

    /**
     * An implementation of a {@link RowMapper} interface method
     * that maps each row of data in the ResultSet.
     *
     * @param rs     the ResultSet to map (pre-initialized for the current row)
     * @param rowNum the number of the current row
     * @return the result Order object for the current row
     * @throws SQLException
     */
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
