package com.labproject.restaurant.dao.impl;

import com.labproject.restaurant.dao.RoleDao;
import com.labproject.restaurant.entities.Role;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;

public class RoleDaoImpl implements RoleDao {

    private DataSource dataSource;
    private static final Logger LOGGER = Logger.getLogger(RoleDaoImpl.class);

    @Override
    public Role getRoleByLogin(String login) {
        String query = "SELECT ROLE.ID,ROLE.NAME FROM ROLE, USER WHERE ROLEID = ROLE.ID AND LOGIN = ?";
        Role role = new Role();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, login);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                role.setId(result.getLong("ID"));
                role.setName(result.getString("NAME"));
            }
        } catch (SQLException e) {
            LOGGER.error("Error: " + e.getMessage(), e);
        }
        return role;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Role getById(long id) {
        String query = "SELECT * FROM ROLE WHERE ID = ?";
        Role role = new Role();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                role.setId(result.getLong("ID"));
                role.setName(result.getString("NAME"));
            }
        } catch (SQLException e) {
            LOGGER.error("Error: " + e.getMessage(), e);
        }

        return role;
    }

    @Override
    public void insert(Role role) {
        String query = "INSERT INTO ROLE (NAME) VALUES (?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, role.getName());
            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                role.setId(result.getLong(1));
            }
        } catch (SQLException e) {
            LOGGER.error("Error: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Role role) {
        String query = "UPDATE ROLE SET NAME = ? WHERE ID = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, role.getName());
            statement.setLong(2, role.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Role role) {
        String query = "DELETE FROM ROLE WHERE ID = ?";
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setLong(1, role.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error: " + e.getMessage(), e);
        }

    }
}

