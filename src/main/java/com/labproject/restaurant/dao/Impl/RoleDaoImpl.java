package com.labproject.restaurant.dao.Impl;

import com.labproject.restaurant.dao.RoleDao;
import com.labproject.restaurant.entities.RoleEntity;

import javax.sql.DataSource;
import java.sql.*;

public class RoleDaoImpl implements RoleDao {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public RoleEntity getById(long id) {
        String query = "SELECT * FROM role WHERE id = ?";
        RoleEntity role = null;
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                role = new RoleEntity();
                role.setId(result.getLong("id"));
                role.setName(result.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public void insert(RoleEntity role) {
        String query = "INSERT INTO role (name) VALUES (?)";
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, role.getName());
            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                role.setId(result.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(RoleEntity role) {
        String query = "UPDATE role SET name = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, role.getName());
            statement.setLong(2, role.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(RoleEntity role) {
        String query = "DELETE FROM role WHERE id = ?";
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setLong(1, role.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
