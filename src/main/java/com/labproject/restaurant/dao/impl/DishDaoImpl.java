package com.labproject.restaurant.dao.impl;

import com.labproject.restaurant.dao.DishDao;
import com.labproject.restaurant.entities.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.annotation.PostConstruct;
import java.util.List;

public class DishDaoImpl implements DishDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SimpleJdbcInsert simpleJdbcInsert;


    @PostConstruct
    public void init() {
        simpleJdbcInsert.withTableName("DISH").usingGeneratedKeyColumns("ID");
    }

    @Override
    public void insert(Dish dish) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("NAME", dish.getName())
                .addValue("DESCRIPTION", dish.getDescription())
                .addValue("PRICE", dish.getPrice());
        dish.setId(simpleJdbcInsert.executeAndReturnKey(parameters).longValue());
    }

    @Override
    public void update(Dish dish) {
        final String UPDATE = "UPDATE DISH SET NAME = COALESCE(?, NAME)," +
                " DESCRIPTION = COALESCE(?, DESCRIPTION), PRICE = COALESCE(?, PRICE) WHERE ID = ?";
        jdbcTemplate.update(UPDATE,
                dish.getName(),
                dish.getDescription(),
                dish.getPrice(),
                dish.getId());
    }

    @Override
    public void delete(Dish dish) {
        final String DELETE = "DELETE FROM DISH WHERE ID = ?";
        jdbcTemplate.update(DELETE, dish.getId());
    }

    @Override
    public List<Dish> getAll() {
        final String FIND_ALL = "SELECT * FROM DISH";
        return jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<>(Dish.class));
    }

    @Override
    public Dish getById(long id) {
        final String FIND_BY_ID = "SELECT * FROM DISH WHERE ID = ?";
        List<Dish> dishList = jdbcTemplate.query(FIND_BY_ID,
                new BeanPropertyRowMapper<>(Dish.class),
                id);
        return dishList.isEmpty() ? new Dish() : dishList.get(0);
    }
}