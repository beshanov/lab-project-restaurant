package com.labproject.restaurant.dao.impl;

import com.labproject.restaurant.dao.UserDao;
import com.labproject.restaurant.dao.mapping.UserMapper;
import com.labproject.restaurant.entities.User;
import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;


    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("USER").usingGeneratedKeyColumns("id");
    }

    @Override
    public User getById(long id) {
        String query = "SELECT * FROM USER WHERE ID = ?";
        User user = new User();
        try {
            user = jdbcTemplate.queryForObject(query, new UserMapper(), id);
        } catch (EmptyResultDataAccessException e) {
        }
        return user;
    }

    @Override
    public User getByLogin(String login) {
        String query = "SELECT * FROM USER WHERE LOGIN = ?";
        User user = new User();
        try {
            user = jdbcTemplate.queryForObject(query, new UserMapper(), login);
        } catch (EmptyResultDataAccessException e) {
        }
        return user;
    }

    @Override
    public void insert(User user) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("LASTNAME", user.getLastname());
        parameters.put("FIRSTNAME", user.getFirstname());
        parameters.put("LOGIN", user.getLogin());
        parameters.put("PASSWORD", user.getPassword());
        parameters.put("ROLE_ID", user.getRole().getId());
        user.setId(simpleJdbcInsert.executeAndReturnKey(parameters).longValue());
    }

    @Override
    public void update(User user) {
        String query = "UPDATE USER SET LASTNAME = ?, FIRSTNAME = ?, LOGIN = ?, `PASSWORD` = ?, ROLE_ID = ? WHERE ID = ?";
        jdbcTemplate.update(query, user.getLastname(), user.getFirstname(), user.getLogin(), user.getPassword(),
                user.getRole().getId(), user.getId());
    }

    @Override
    public void delete(User user) {
        String query = "DELETE FROM USER WHERE ID = ?";
        jdbcTemplate.update(query, user.getId());
    }
}
