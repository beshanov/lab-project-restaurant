package com.labproject.restaurant.dao.impl;

import com.labproject.restaurant.dao.OrderDao;
import com.labproject.restaurant.dao.mapping.OrderMapper;
import com.labproject.restaurant.entities.Order;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    private static final Logger LOGGER = Logger.getLogger(OrderDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SimpleJdbcInsert simpleJdbcInsert;

    @PostConstruct
    public void init() {
        simpleJdbcInsert.withTableName("`ORDER`").usingGeneratedKeyColumns("ID");
    }

    @Override
    public void insert(Order order) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("ORDERDATE", order.getOrderDate())
                .addValue("USER_ID", order.getUser().getId())
                .addValue("STATUS_ID", order.getStatus().getId())
                .addValue("ADMIN_ID", order.getAdmin().getId())
                .addValue("AMOUNT", order.getAmount());
        order.setId(simpleJdbcInsert.executeAndReturnKey(parameters).longValue());
    }

    @Override
    public Order getById(long orderId) {
        final String query = "SELECT * FROM `ORDER` WHERE ID = ?";
        List<Order> list = jdbcTemplate.query(query, new OrderMapper(), orderId);
        return list.isEmpty() ? new Order() : list.get(0);
    }

    @Override
    public List<Order> getAll() {
        final String query = "SELECT * FROM `ORDER`";
        return jdbcTemplate.query(query, new OrderMapper());
    }

    @Override
    public List<Order> getAllByUserId(long userId) {
        String query = "SELECT * FROM `ORDER` WHERE USER_ID = ?";
        return jdbcTemplate.query(query, new OrderMapper(), userId);
    }

    @Override
    public void update(Order order) {
        final String query = "UPDATE `ORDER` SET ORDERDATE = ?, USER_ID = ?," +
                " STATUS_ID = ?, ADMIN_ID=?, AMOUNT=? WHERE ID = ?";
        jdbcTemplate.update(query,
                order.getOrderDate(),
                order.getUser().getId(),
                order.getStatus().getId(),
                order.getAdmin().getId(),
                order.getAmount(),
                order.getId());
    }

    @Override
    public void deleteById(long orderId) {
        final String query = "DELETE FROM `ORDER` WHERE ID = ?";
        jdbcTemplate.update(query, orderId);
    }
}
