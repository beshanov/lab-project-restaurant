package com.labproject.restaurant.dao.impl;

import com.labproject.restaurant.dao.UserDao;
import com.labproject.restaurant.dao.mapping.UserMapper;
import com.labproject.restaurant.entities.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.annotation.PostConstruct;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SimpleJdbcInsert simpleJdbcInsert;

    @PostConstruct
    public void init() {
        simpleJdbcInsert.withTableName("USER").usingGeneratedKeyColumns("ID");
    }

    @Override
    public User getById(long id) {
        String query = "SELECT * FROM USER WHERE ID = ?";
        List<User> list = jdbcTemplate.query(query, new UserMapper(), id);
        return list.isEmpty() ? new User() : list.get(0);
    }

    @Override
    public User getByLogin(String login) {
        String query = "SELECT * FROM USER WHERE LOGIN = ?";
        List<User> list = jdbcTemplate.query(query, new UserMapper(), login);
        return list.isEmpty() ? new User() : list.get(0);
    }

    @Override
    public void insert(User user) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("LASTNAME", user.getLastname())
                .addValue("FIRSTNAME", user.getFirstname())
                .addValue("LOGIN", user.getLogin())
                .addValue("PASSWORD", user.getPassword())
                .addValue("ROLE_ID", user.getRole().getId());
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
