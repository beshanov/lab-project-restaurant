package com.labproject.restaurant.dao.impl;

import com.labproject.restaurant.dao.RoleDao;
import com.labproject.restaurant.entities.Role;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;

public class RoleDaoImpl implements RoleDao {

    private DataSource dataSource;
    private final Logger logger = LogManager.getRootLogger();

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }



    @Override
    public Role getById(long id) {
        String query = "SELECT * FROM role WHERE id = ?";
        Role role = new Role();
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                role.setId(result.getLong("id"));
                role.setName(result.getString("name"));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        }
        return role;
    }

    @Override
    public void insert(Role role) {
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
            logger.error(e.getMessage(),e);
        }
    }

    @Override
    public void update(Role role) {
        String query = "UPDATE role SET name = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, role.getName());
            statement.setLong(2, role.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        }
    }

    @Override
    public void delete(Role role) {
        String query = "DELETE FROM role WHERE id = ?";
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setLong(1, role.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        }

    }
}
