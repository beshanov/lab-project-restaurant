package com.labproject.restaurant.dao.mapping;

import com.labproject.restaurant.entities.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * An implementation of a {@link RowMapper} interface
 * that performs the actual work of mapping each row to a {@link Role} object
 *
 * @author Igor Pevtsov
 * @author Alexandr Zorin
 * @author Vsevolod Beshanov
 * @author Zhanna Fedorova
 * @see RowMapper
 * @see Role
 */
public class RoleMapper implements RowMapper<Role> {

    /**
     * An implementation of a {@link RowMapper} interface method
     * that maps each row of data in the ResultSet.
     *
     * @param rs     the ResultSet to map (pre-initialized for the current row)
     * @param rowNum the number of the current row
     * @return the result Role object for the current row
     * @throws SQLException
     */
    @Override
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
        Role role = new Role();
        role.setId(rs.getLong("ID"));
        role.setName(rs.getString("NAME"));
        return role;
    }
}
