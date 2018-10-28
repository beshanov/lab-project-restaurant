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

import javax.annotation.PostConstruct;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private static final Logger LOGGER = Logger.getLogger(OrderDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SimpleJdbcInsert simpleJdbcInsert;


    @PostConstruct
    public void init() {
        simpleJdbcInsert.withTableName("ORDER").usingGeneratedKeyColumns("ID");
    }

    @Override
    public void insert(Order order) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("ORDERDATE", order.getOrderDate())
                .addValue("USER_ID", order.getUser().getId())
                .addValue("STATUS_ID", order.getStatus().getId());
        order.setId(simpleJdbcInsert.executeAndReturnKey(parameters).longValue());
    }

    @Override
    public Order getById(long orderId) {
        String query = "SELECT * FROM `ORDER` WHERE ID = ?";
        List<Order> list = jdbcTemplate.query(query, new OrderMapper(), orderId);
        return list.isEmpty() ? new Order() : list.get(0);
    }

    @Override
    public List<Order> getAll() {
        String query = "SELECT * FROM `ORDER`";
        return jdbcTemplate.query(query, new OrderMapper());
    }

    @Override
    public void update(Order order) {
        String query = "UPDATE `ORDER` SET ORDERDATE = ?, USER_ID = ?, STATUS_ID = ? WHERE ID = ?";
        jdbcTemplate.update(query,
                order.getOrderDate(),
                order.getUser().getId(),
                order.getStatus().getId(),
                order.getId());
    }

    @Override
    public void deleteById(long orderId) {
        String query = "DELETE FROM `ORDER` WHERE ID = ?";
        jdbcTemplate.update(query, orderId);
    }
}
