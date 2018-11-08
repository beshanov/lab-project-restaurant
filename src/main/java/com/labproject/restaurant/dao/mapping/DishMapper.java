package com.labproject.restaurant.dao.mapping;

import com.labproject.restaurant.entities.Dish;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DishMapper implements RowMapper<Dish> {
    @Override
    public Dish mapRow(ResultSet rs, int rowNum) throws SQLException {
        Dish dish = new Dish();
        dish.setId(rs.getLong("ID"));
        dish.setDescription(rs.getString("DESCRIPTION"));
        dish.setName(rs.getString("NAME"));
        dish.setPrice(rs.getBigDecimal("PRICE"));
        dish.setDeleted(rs.getBoolean("IS_DELETED"));
        return dish;
    }
}