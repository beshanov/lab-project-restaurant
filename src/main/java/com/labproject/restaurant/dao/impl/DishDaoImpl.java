package com.labproject.restaurant.dao.impl;

import com.labproject.restaurant.dao.DishDao;
import com.labproject.restaurant.entities.Dish;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class DishDaoImpl implements DishDao {

    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void insert(Dish dish) {
        final String SAVE = "INSERT INTO DISH(NAME, DESCRIPTION , PRICE) VALUES(?,?,?)";
        jdbcTemplate.update(SAVE,
                dish.getName(),
                dish.getDescription(),
                dish.getPrice());
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
        try {
            return jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<>(Dish.class));
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public Dish getById(long id) {
        final String FIND_BY_ID = "SELECT * FROM DISH WHERE ID = ?";
        try {
            return jdbcTemplate.queryForObject(FIND_BY_ID,
                    new BeanPropertyRowMapper<>(Dish.class),
                    id);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }
}