package com.labproject.restaurant.dao.impl;

import com.labproject.restaurant.dao.DishDao;
import com.labproject.restaurant.dao.mapping.DishMapper;
import com.labproject.restaurant.entities.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
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
                .addValue("PRICE", dish.getPrice())
                .addValue("IS_DELETED", false);
        dish.setId(simpleJdbcInsert.executeAndReturnKey(parameters).longValue());
    }

    @Override
    public void update(Dish dish) {
        final String UPDATE = "UPDATE DISH SET NAME = COALESCE(?, NAME)," +
                " DESCRIPTION = COALESCE(?, DESCRIPTION), PRICE = COALESCE(?, PRICE), IS_DELETED = ? WHERE ID = ?";
        jdbcTemplate.update(UPDATE,
                dish.getName(),
                dish.getDescription(),
                dish.getPrice(),
                dish.isDeleted(),
                dish.getId());
    }

    @Override
    public void updateIsDeleted(Dish dish) {
        final String UPDATE = "UPDATE DISH SET IS_DELETED = ? WHERE ID = ?";
        jdbcTemplate.update(UPDATE,
                dish.isDeleted(),
                dish.getId());
    }

    @Override
    public List<Dish> getAll() {
        final String FIND_ALL = "SELECT * FROM DISH";
        return jdbcTemplate.query(FIND_ALL, new DishMapper());
    }

    @Override
    public List<Dish> getAvailable() {
        final String FIND_AVAILABLE = "SELECT * FROM DISH WHERE IS_DELETED=FALSE";
        return jdbcTemplate.query(FIND_AVAILABLE, new DishMapper());
    }

    @Override
    public Dish getById(long id) {
        final String FIND_BY_ID = "SELECT * FROM DISH WHERE ID = ?";
        List<Dish> dishList = jdbcTemplate.query(FIND_BY_ID,
                new DishMapper(), id);
        return dishList.isEmpty() ? new Dish() : dishList.get(0);
    }

    @Override
    public List<Dish> getPage(int page,int countPerPage, boolean deleted) {
        int from = (page - 1) * countPerPage;
        final String FIND_PAGE = "SELECT * FROM DISH WHERE IS_DELETED = ? LIMIT ?,?";
        return jdbcTemplate.query(FIND_PAGE, new DishMapper(),deleted,from,countPerPage);
    }

    @Override
    public int dishesCount(boolean deleted) {
        final String DISH_COUNT = "SELECT COUNT(*) FROM DISH WHERE IS_DELETED = ?";
        return jdbcTemplate.queryForObject(DISH_COUNT,Integer.class,deleted);
    }


}