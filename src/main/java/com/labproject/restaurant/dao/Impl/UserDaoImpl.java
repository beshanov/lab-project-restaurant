package com.labproject.restaurant.dao.Impl;

import com.labproject.restaurant.dao.UserDao;
import com.labproject.restaurant.entities.RoleEntity;
import com.labproject.restaurant.entities.UserEntity;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public UserEntity getById(long id) {
        String query = "SELECT * FROM user LEFT JOIN role ON user.roleid = role.id WHERE user.id = ?";
        UserEntity user = null;
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                user = new UserEntity();
                user.setId(result.getLong("user.id"));
                user.setLastname(result.getString("lastname"));
                user.setFirstname(result.getString("firstname"));
                user.setLogin(result.getString("login"));
                user.setPwd(result.getString("pwd"));
                RoleEntity role = new RoleEntity();
                role.setId(result.getLong("role.id"));
                role.setName(result.getString("name"));
                user.setRole(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void insert(UserEntity user) {
        String query = "INSERT INTO user (id, lastname, firstname, login, pwd, roleid) VALUES (?,?,?,?,?,?)";
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setLong(1, user.getId());
            statement.setString(2, user.getLastname());
            statement.setString(3, user.getFirstname());
            statement.setString(4, user.getLogin());
            statement.setString(5, user.getPwd());
            statement.setLong(6, user.getRole().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(UserEntity user) {
        String query = "UPDATE user, role SET lastname = ?, firstname = ?, login = ?, pwd = ?, roleid = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, user.getLastname());
            statement.setString(2, user.getFirstname());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPwd());
            statement.setLong(5, user.getRole().getId());
            statement.setLong(6, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(UserEntity user) {
        String query = "DELETE FROM user where id = ?";
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setLong(1, user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
