package com.labproject.restaurant.dao.impl;

import com.labproject.restaurant.dao.RoleDao;
import com.labproject.restaurant.dao.mapping.RoleMapper;
import com.labproject.restaurant.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Data Access Object class for the {@link Role} objects that implements the
 * {@link RoleDao} interface
 *
 * @author Zhanna Fedorova
 */
@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SimpleJdbcInsert simpleJdbcInsert;

    @PostConstruct
    public void init() {
        simpleJdbcInsert.withTableName("ROLE").usingGeneratedKeyColumns("id");
    }

    @Override
    public Role getById(long id) {
        String query = "SELECT * FROM ROLE WHERE ID = ?";
        List<Role> list = jdbcTemplate.query(query, new RoleMapper(), id);
        return list.isEmpty() ? new Role() : list.get(0);
    }

    @Override
    public Role getRoleByLogin(String login) {
        String query = "SELECT ROLE.ID,ROLE.NAME FROM ROLE, USER WHERE ROLE_ID = ROLE.ID AND LOGIN = ?";
        List<Role> list = jdbcTemplate.query(query, new RoleMapper(), login);
        return list.isEmpty() ? new Role() : list.get(0);
    }

    @Override
    public List<Role> getAllRoles() {
        String query = "SELECT * FROM ROLE";
        return jdbcTemplate.query(query, new RoleMapper());
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

