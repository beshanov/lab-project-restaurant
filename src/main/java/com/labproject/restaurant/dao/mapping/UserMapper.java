package com.labproject.restaurant.dao.mapping;

import com.labproject.restaurant.entities.Role;
import com.labproject.restaurant.entities.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("ID"));
        user.setFirstname(rs.getString("FIRSTNAME"));
        user.setLastname(rs.getString("LASTNAME"));
        user.setLogin(rs.getString("LOGIN"));
        user.setPassword(rs.getString("PASSWORD"));
        Role role = new Role();
        role.setId(rs.getLong("ROLE_ID"));
        user.setRole(role);
        return user;
    }
}
