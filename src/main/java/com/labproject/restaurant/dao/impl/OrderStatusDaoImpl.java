package com.labproject.restaurant.dao.impl;

import com.labproject.restaurant.dao.OrderStatusDao;
import com.labproject.restaurant.entities.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderStatusDaoImpl implements OrderStatusDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public OrderStatus getById(long orderStatusId) {
        String query = "SELECT * FROM ORDER_STATUS WHERE ID = ?";
        List<OrderStatus> list = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(OrderStatus.class), orderStatusId);
        return list.isEmpty() ? new OrderStatus() : list.get(0);
    }

    @Override
    public List<OrderStatus> getAll() {
        String query = "SELECT * FROM ORDER_STATUS";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(OrderStatus.class));
    }
}
