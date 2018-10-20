package com.labproject.restaurant.dao.impl;

import com.labproject.restaurant.dao.UserDao;
import com.labproject.restaurant.entities.Role;
import com.labproject.restaurant.entities.User;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;

public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User getById(long id) {
        String query = "SELECT * FROM user WHERE user.id = ?";
        User user = new User();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                user.setId(result.getLong("id"));
                user.setLastname(result.getString("lastname"));
                user.setFirstname(result.getString("firstname"));
                user.setLogin(result.getString("login"));
                user.setPassword(result.getString("password"));
                Role role = new Role();
                role.setId(result.getLong("roleid"));
                user.setRole(role);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return user;
    }

    @Override
    public User getByLogin(String login) {
        String query = "SELECT * FROM user WHERE user.login = ?";
        User user = new User();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, login);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                user.setId(result.getLong("id"));
                user.setLastname(result.getString("lastname"));
                user.setFirstname(result.getString("firstname"));
                user.setLogin(result.getString("login"));
                user.setPassword(result.getString("password"));
                Role role = new Role();
                role.setId(result.getLong("roleid"));
                user.setRole(role);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return user;
    }

    @Override
    public void insert(User user) {
        String query = "INSERT INTO user (lastname, firstname, login, `password`, roleid) VALUES (?,?,?,?,?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getLastname());
            statement.setString(2, user.getFirstname());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setLong(5, user.getRole().getId());
            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                user.setId(result.getLong(1));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void update(User user) {
        String query = "UPDATE user SET lastname = ?, firstname = ?, login = ?, `password` = ?, roleid = ? WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, user.getLastname());
            statement.setString(2, user.getFirstname());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setLong(5, user.getRole().getId());
            statement.setLong(6, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void delete(User user) {
        String query = "DELETE FROM user WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setLong(1, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
