package com.labproject.restaurant.dao.impl;

import com.labproject.restaurant.dao.RoleDao;
import com.labproject.restaurant.dao.mapping.RoleMapper;
import com.labproject.restaurant.entities.Role;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;

public class RoleDaoImpl implements RoleDao {

    private static final Logger LOGGER = Logger.getLogger(RoleDaoImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public RoleDaoImpl(DataSource dataSource) {
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("ROLE").usingGeneratedKeyColumns("id");
    }

    @Override
    public Role getById(long id) {
        String query = "SELECT * FROM ROLE WHERE ID = ?";
        Role role = new Role();
        try {
            role = jdbcTemplate.queryForObject(query, new RoleMapper(), id);
        } catch (EmptyResultDataAccessException e) {
        }
        return role;
    }

    @Override
    public Role getRoleByLogin(String login) {
        String query = "SELECT ROLE.ID,ROLE.NAME FROM ROLE, USER WHERE ROLE_ID = ROLE.ID AND LOGIN = ?";
        Role role = new Role();
        try {
            role = jdbcTemplate.queryForObject(query, new RoleMapper(), login);
        } catch (EmptyResultDataAccessException e) {
        }
        return role;
    }


    @Override
    public void insert(Role role) {
        role.setId(simpleJdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(role)).longValue());
    }

    @Override
    public void update(Role role) {
        String query = "UPDATE ROLE SET NAME = ? WHERE ID = ?";
        jdbcTemplate.update(query, role.getName(), role.getId());
    }

    @Override
    public void delete(Role role) {
        String query = "DELETE FROM ROLE WHERE ID = ?";
        jdbcTemplate.update(query, role.getId());
    }
}

