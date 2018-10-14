package com.labproject.restaurant.dao.impl;

import com.labproject.restaurant.dao.DishDao;
import com.labproject.restaurant.entities.DishEntity;
import lombok.Setter;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DishDaoImpl implements DishDao {

    @Setter
    private DataSource dataSource;

    @Override
    public void save(DishEntity dishEntity) {
        final String SAVE = "INSERT INTO dish(name, description , price) VALUES(?,?,?)";
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(SAVE);
            statement.setString(1, dishEntity.getName());
            statement.setString(2, dishEntity.getDescription());
            statement.setInt(3, dishEntity.getPrice());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(DishEntity dishEntity) {
        final String UPDATE = "UPDATE dish SET name=COALESCE(?, name)," +
                " description=COALESCE(?, description), price=COALESCE(?, price) WHERE id=?";
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE);
            statement.setString(1, dishEntity.getName());
            statement.setString(2, dishEntity.getDescription());
            statement.setInt(3, dishEntity.getPrice());
            statement.setLong(4, dishEntity.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(DishEntity dishEntity) {
        final String DELETE = "DELETE FROM dish WHERE id = ?";
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE);
            statement.setLong(1, dishEntity.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<DishEntity> findAll() {
        final String FIND_ALL = "SELECT * FROM dish";
        DishEntity dishEntity;
        List<DishEntity> dishEntities = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_ALL);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                dishEntity = new DishEntity();
                dishEntity.setId(rs.getLong("id"));
                dishEntity.setName(rs.getString("name"));
                dishEntity.setDescription(rs.getString("description"));
                dishEntity.setPrice(rs.getInt("price"));
                dishEntities.add(dishEntity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dishEntities;
    }

    @Override
    public DishEntity findById(long id) {
        final String FIND_BY_ID = "SELECT * FROM dish WHERE id=?";
        DishEntity dishEntity = null;
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_BY_ID);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                dishEntity = new DishEntity();
                dishEntity.setId(rs.getLong("id"));
                dishEntity.setName(rs.getString("name"));
                dishEntity.setDescription(rs.getString("description"));
                dishEntity.setPrice(rs.getInt("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dishEntity;
    }
}