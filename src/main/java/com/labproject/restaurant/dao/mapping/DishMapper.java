package com.labproject.restaurant.dao.mapping;

import com.labproject.restaurant.entities.Dish;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * An implementation of a {@link RowMapper} interface
 * that performs the actual work of mapping each row to a {@link Dish} object
 *
 * @author Igor Pevtsov
 * @author Alexandr Zorin
 * @author Vsevolod Beshanov
 * @author Zhanna Fedorova
 * @see RowMapper
 * @see Dish
 */
public class DishMapper implements RowMapper<Dish> {

    /**
     * An implementation of a {@link RowMapper} interface method
     * that maps each row of data in the ResultSet.
     *
     * @param rs     the ResultSet to map (pre-initialized for the current row)
     * @param rowNum the number of the current row
     * @return the result Dish object for the current row
     * @throws SQLException
     */
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