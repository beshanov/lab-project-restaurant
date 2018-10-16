package com.labproject.restaurant.dao.impl;

import com.labproject.restaurant.dao.DishDao;
import com.labproject.restaurant.entities.DishEntity;
import org.apache.commons.dbutils.DbUtils;
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
    private final Logger logger = Logger.getLogger(RoleDaoImpl.class);

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insert(DishEntity dishEntity) {
        final String SAVE = "INSERT INTO dish(name, description , price) VALUES(?,?,?)";
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = dataSource.getConnection();
            statement = conn.prepareStatement(SAVE);
            statement.setString(1, dishEntity.getName());
            statement.setString(2, dishEntity.getDescription());
            statement.setDouble(3, dishEntity.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error: " + e.getMessage(), e);
        } finally {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(conn);
        }

    }

    @Override
    public void update(DishEntity dishEntity) {
        final String UPDATE = "UPDATE dish SET name=COALESCE(?, name)," +
                " description=COALESCE(?, description), price=COALESCE(?, price) WHERE id=?";
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = dataSource.getConnection();
            statement = conn.prepareStatement(UPDATE);
            statement.setString(1, dishEntity.getName());
            statement.setString(2, dishEntity.getDescription());
            statement.setDouble(3, dishEntity.getPrice());
            statement.setLong(4, dishEntity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error: " + e.getMessage(), e);
        } finally {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(conn);
        }
    }

    @Override
    public void delete(DishEntity dishEntity) {
        final String DELETE = "DELETE FROM dish WHERE id = ?";
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = dataSource.getConnection();
            statement = conn.prepareStatement(DELETE);
            statement.setLong(1, dishEntity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error: " + e.getMessage(), e);
        } finally {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(conn);
        }
    }

    @Override
    public List<DishEntity> findAll() {
        final String FIND_ALL = "SELECT * FROM dish";
        DishEntity dishEntity;
        List<DishEntity> dishEntities = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            statement = conn.prepareStatement(FIND_ALL);
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
            logger.error("Error: " + e.getMessage(), e);
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(conn);
        }
        return dishEntities;
    }

    @Override
    public DishEntity findById(long id) {
        final String FIND_BY_ID = "SELECT * FROM dish WHERE id=?";
        DishEntity dishEntity = null;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            statement = conn.prepareStatement(FIND_BY_ID);
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
            logger.error("Error: " + e.getMessage(), e);
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(conn);
        }
        return dishEntity;
    }
}