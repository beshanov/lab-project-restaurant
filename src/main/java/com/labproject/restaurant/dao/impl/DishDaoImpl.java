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

    private DataSource dataSource;
    private static final Logger LOGGER = Logger.getLogger(DishDaoImpl.class);

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insert(DishEntity dishEntity) {
        final String SAVE = "INSERT INTO dish(name, description , price) VALUES(?,?,?)";
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
        final String UPDATE = "UPDATE dish SET name=COALESCE(?, name)," +
                " description=COALESCE(?, description), price=COALESCE(?, price) WHERE id=?";
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
        final String DELETE = "DELETE FROM dish WHERE id = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement statement = conn.prepareStatement(DELETE)) {
            statement.setLong(1, dishEntity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error: " + e.getMessage(), e);
        }
    }

    @Override
    public List<DishEntity> getAll() {
        final String FIND_ALL = "SELECT * FROM dish";
        DishEntity dishEntity;
        List<DishEntity> dishEntities = new ArrayList<>();
        ResultSet rs = null;
        try (Connection conn = dataSource.getConnection(); PreparedStatement statement = conn.prepareStatement(FIND_ALL)) {
            rs = statement.executeQuery();
            while (rs.next()) {
                dishEntity = new DishEntity();
                dishEntity.setId(rs.getLong("id"));
                dishEntity.setName(rs.getString("name"));
                dishEntity.setDescription(rs.getString("description"));
                dishEntity.setPrice(rs.getDouble("price"));
                dishEntities.add(dishEntity);
            }
        } catch (SQLException e) {
            LOGGER.error("Error: " + e.getMessage(), e);
        }
        return dishEntities;
    }

    @Override
    public DishEntity getById(long id) {
        final String FIND_BY_ID = "SELECT * FROM dish WHERE id=?";
        DishEntity dishEntity = null;
        ResultSet rs = null;
        try (Connection conn = dataSource.getConnection(); PreparedStatement statement = conn.prepareStatement(FIND_BY_ID)) {
            statement.setLong(1, id);
            rs = statement.executeQuery();
            if (rs.next()) {
                dishEntity = new DishEntity();
                dishEntity.setId(rs.getLong("id"));
                dishEntity.setName(rs.getString("name"));
                dishEntity.setDescription(rs.getString("description"));
                dishEntity.setPrice(rs.getDouble("price"));
            }
        } catch (SQLException e) {
            LOGGER.error("Error: " + e.getMessage(), e);
        }
        return dishEntity;
    }
}