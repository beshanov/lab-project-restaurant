package com.labproject.restaurant.dao.mapping;

import com.labproject.restaurant.entities.Role;
import com.labproject.restaurant.entities.User;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * An implementation of a {@link RowMapper} interface
 * that performs the actual work of mapping each row to a {@link User} object
 *
 * @author Igor Pevtsov
 * @author Alexandr Zorin
 * @author Vsevolod Beshanov
 * @author Zhanna Fedorova
 * @see RowMapper
 * @see User
 */
public class UserMapper implements RowMapper<User> {

    private static final Logger LOGGER = Logger.getLogger(UserMapper.class);

    /**
     * An implementation of a {@link RowMapper} interface method
     * that maps each row of data in the ResultSet.
     *
     * @param rs     the ResultSet to map (pre-initialized for the current row)
     * @param rowNum the number of the current row
     * @return the result User object for the current row
     * @throws SQLException
     */
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
