package com.labproject.restaurant.dao.impl;

import com.labproject.restaurant.dao.OrderDishDao;
import com.labproject.restaurant.entities.Dish;
import com.labproject.restaurant.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderDishDaoImpl implements OrderDishDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addDishToOrder(Dish dish, Order order, int count) {
        final String ADD_DISH_TO_ORDER = "INSERT INTO ORDER_DISH(DISH_ID, ORDER_ID, COUNT) VALUES(?, ?, ?)";
        jdbcTemplate.update(ADD_DISH_TO_ORDER, dish.getId(), order.getId(), count);
    }

    @Override
    public Map<Dish, Integer> getDishesByOrder(Order order) {
        final String query = "SELECT * FROM ORDER_DISH WHERE ORDER_ID = ?";
        List<Map<String, Object>> queryResults = jdbcTemplate.queryForList(query, order.getId());
        Map<Dish, Integer> result = new HashMap<>();

        for (Map map : queryResults) {
            Dish dish = new Dish();
            dish.setId((long) map.get("DISH_ID"));
            result.put(dish, (int) map.get("COUNT"));
        }

        return result;
    }

    @Override
    public void deleteDishFromOrder(Dish dish, Order order) {
        final String DELETE_DISH_FROM_ORDER = "DELETE FROM ORDER_DISH WHERE DISH_ID = ? AND ORDER_ID = ?";
        jdbcTemplate.update(DELETE_DISH_FROM_ORDER, dish.getId(), order.getId());
    }
}