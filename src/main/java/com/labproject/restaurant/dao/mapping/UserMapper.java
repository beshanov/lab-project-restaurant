package com.labproject.restaurant.dao.mapping;

import com.labproject.restaurant.entities.Role;
import com.labproject.restaurant.entities.User;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    private static final Logger LOGGER = Logger.getLogger(UserMapper.class);

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
        try {
            role.setName(rs.getString("NAME"));
        } catch (SQLException e) {
            LOGGER.info("Lazy load, haven't column NAME");
        }
        user.setRole(role);
        return user;
    }
}
