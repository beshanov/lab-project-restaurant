package com.labproject.restaurant.dao.impl;

import com.labproject.restaurant.dao.OrderDishDao;
import com.labproject.restaurant.entities.Dish;
import com.labproject.restaurant.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderDishDaoImpl implements OrderDishDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SimpleJdbcInsert simpleJdbcInsert;

    @PostConstruct
    public void init() {
        simpleJdbcInsert.withTableName("ORDER_DISH").usingGeneratedKeyColumns("ID");
    }

    @Override
    public void addDishToOrder(Dish dish, Order order, int count) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("DISH_ID", dish.getId())
                .addValue("ORDER_ID", order.getId())
                .addValue("COUNT", count);
        order.setId(simpleJdbcInsert.executeAndReturnKey(parameters).longValue());
    }

    @Override
    public Map<Dish, Integer> getDishesByOrderId(long orderId) {
        final String query = "SELECT * FROM ORDER_DISH WHERE ORDER_ID = ?";
        List<Map<String, Object>> queryResults = jdbcTemplate.queryForList(query, orderId);
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

    @Override
    public void deleteDish(Dish dish) {
        final String query = "DELETE FROM ORDER_DISH WHERE DISH_ID = ?";
        jdbcTemplate.update(query, dish.getId());
    }
}