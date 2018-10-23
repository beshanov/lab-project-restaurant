package com.labproject.restaurant.dao.impl;

import com.labproject.restaurant.dao.DishDao;
import com.labproject.restaurant.entities.Dish;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DishDaoImpl implements DishDao {

    private static final Logger LOGGER = Logger.getLogger(DishDaoImpl.class);
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insert(Dish dish) {
        final String SAVE = "INSERT INTO DISH(NAME, DESCRIPTION , PRICE) VALUES(?,?,?)";
        try (Connection conn = dataSource.getConnection(); PreparedStatement statement = conn.prepareStatement(SAVE)) {
            statement.setString(1, dish.getName());
            statement.setString(2, dish.getDescription());
            statement.setDouble(3, dish.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Dish dish) {
        final String UPDATE = "UPDATE DISH SET NAME = COALESCE(?, NAME)," +
                " DESCRIPTION = COALESCE(?, DESCRIPTION), PRICE = COALESCE(?, PRICE) WHERE ID = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement statement = conn.prepareStatement(UPDATE)) {
            statement.setString(1, dish.getName());
            statement.setString(2, dish.getDescription());
            statement.setDouble(3, dish.getPrice());
            statement.setLong(4, dish.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Dish dish) {
        final String DELETE = "DELETE FROM DISH WHERE ID = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement statement = conn.prepareStatement(DELETE)) {
            statement.setLong(1, dish.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Dish> getAll() {
        final String FIND_ALL = "SELECT * FROM DISH";
        Dish dish;
        List<Dish> dishEntities = new ArrayList<>();
        ResultSet rs = null;
        try (Connection conn = dataSource.getConnection(); PreparedStatement statement = conn.prepareStatement(FIND_ALL)) {
            rs = statement.executeQuery();
            while (rs.next()) {
                dish = new Dish();
                dish.setId(rs.getLong("ID"));
                dish.setName(rs.getString("NAME"));
                dish.setDescription(rs.getString("DESCRIPTION"));
                dish.setPrice(rs.getDouble("PRICE"));
                dishEntities.add(dish);
            }
        } catch (SQLException e) {
            LOGGER.error("Error: " + e.getMessage(), e);
        }
        return dishEntities;
    }

    @Override
    public Dish getById(long id) {
        final String FIND_BY_ID = "SELECT * FROM DISH WHERE ID = ?";
        Dish dish = null;
        ResultSet rs = null;
        try (Connection conn = dataSource.getConnection(); PreparedStatement statement = conn.prepareStatement(FIND_BY_ID)) {
            statement.setLong(1, id);
            rs = statement.executeQuery();
            if (rs.next()) {
                dish = new Dish();
                dish.setId(rs.getLong("ID"));
                dish.setName(rs.getString("NAME"));
                dish.setDescription(rs.getString("DESCRIPTION"));
                dish.setPrice(rs.getDouble("PRICE"));
            }
        } catch (SQLException e) {
            LOGGER.error("Error: " + e.getMessage(), e);
        }
        return dish;
    }
}