package com.labproject.restaurant.dao.mapping;

import com.labproject.restaurant.entities.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class RoleMapper implements RowMapper<Role> {

    @Override
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
        Role role = new Role();
        role.setId(rs.getLong("ID"));
        role.setName(rs.getString("NAME"));
        return role;
    }
}
