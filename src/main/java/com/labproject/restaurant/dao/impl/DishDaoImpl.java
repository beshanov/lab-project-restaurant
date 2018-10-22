package com.labproject.restaurant.dao.impl;

import com.labproject.restaurant.dao.DishDao;
import com.labproject.restaurant.entities.DishEntity;
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
    public void insert(DishEntity dishEntity) {
        final String SAVE = "INSERT INTO DISH(NAME, DESCRIPTION , PRICE) VALUES(?,?,?)";
        try (Connection conn = dataSource.getConnection(); PreparedStatement statement = conn.prepareStatement(SAVE)) {
            statement.setString(1, dishEntity.getName());
            statement.setString(2, dishEntity.getDescription());
            statement.setDouble(3, dishEntity.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(DishEntity dishEntity) {
        final String UPDATE = "UPDATE DISH SET NAME = COALESCE(?, NAME)," +
                " DESCRIPTION = COALESCE(?, DESCRIPTION), PRICE = COALESCE(?, PRICE) WHERE ID = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement statement = conn.prepareStatement(UPDATE)) {
            statement.setString(1, dishEntity.getName());
            statement.setString(2, dishEntity.getDescription());
            statement.setDouble(3, dishEntity.getPrice());
            statement.setLong(4, dishEntity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(DishEntity dishEntity) {
        final String DELETE = "DELETE FROM DISH WHERE ID = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement statement = conn.prepareStatement(DELETE)) {
            statement.setLong(1, dishEntity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error: " + e.getMessage(), e);
        }
    }

    @Override
    public List<DishEntity> getAll() {
        final String FIND_ALL = "SELECT * FROM DISH";
        DishEntity dishEntity;
        List<DishEntity> dishEntities = new ArrayList<>();
        ResultSet rs = null;
        try (Connection conn = dataSource.getConnection(); PreparedStatement statement = conn.prepareStatement(FIND_ALL)) {
            rs = statement.executeQuery();
            while (rs.next()) {
                dishEntity = new DishEntity();
                dishEntity.setId(rs.getLong("ID"));
                dishEntity.setName(rs.getString("NAME"));
                dishEntity.setDescription(rs.getString("DESCRIPTION"));
                dishEntity.setPrice(rs.getDouble("PRICE"));
                dishEntities.add(dishEntity);
            }
        } catch (SQLException e) {
            LOGGER.error("Error: " + e.getMessage(), e);
        }
        return dishEntities;
    }

    @Override
    public DishEntity getById(long id) {
        final String FIND_BY_ID = "SELECT * FROM DISH WHERE ID = ?";
        DishEntity dishEntity = null;
        ResultSet rs = null;
        try (Connection conn = dataSource.getConnection(); PreparedStatement statement = conn.prepareStatement(FIND_BY_ID)) {
            statement.setLong(1, id);
            rs = statement.executeQuery();
            if (rs.next()) {
                dishEntity = new DishEntity();
                dishEntity.setId(rs.getLong("ID"));
                dishEntity.setName(rs.getString("NAME"));
                dishEntity.setDescription(rs.getString("DESCRIPTION"));
                dishEntity.setPrice(rs.getDouble("PRICE"));
            }
        } catch (SQLException e) {
            LOGGER.error("Error: " + e.getMessage(), e);
        }
        return dishEntity;
    }
}