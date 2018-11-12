package com.labproject.restaurant.dao;

import com.labproject.restaurant.dao.impl.UserDaoImpl;
import com.labproject.restaurant.entities.Role;
import com.labproject.restaurant.entities.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.internal.matchers.StartsWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;
    @Mock
    private SimpleJdbcInsert simpleJdbcInsert;
    @InjectMocks
    UserDaoImpl userDao;

    @Before
    public void setUp() {
        when(simpleJdbcInsert.executeAndReturnKey(any(SqlParameterSource.class))).thenReturn(new Long(1));
    }


    @Test
    public void testGetById() {
        long id = 1;
        userDao.getById(id);
        verify(jdbcTemplate).query(argThat(new StartsWith("SELECT")), any(RowMapper.class), eq(id));
    }

    @Test
    public void testGetByLogin() {
        String login = "login";
        userDao.getByLogin(login);
        verify(jdbcTemplate).query(argThat(new StartsWith("SELECT")), any(RowMapper.class), eq(login));
    }

    @Test
    public void testGetAllUsers() {
        userDao.getAllUsers(true);
        verify(jdbcTemplate).query(argThat(new StartsWith("SELECT")), any(RowMapper.class));
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setRole(new Role());
        userDao.insert(user);
        verify(simpleJdbcInsert).executeAndReturnKey(any(SqlParameterSource.class));
    }

    @Test
    public void testUpdateDetails() {
        User user = new User();
        userDao.updateDetails(user);
        verify(jdbcTemplate).update(argThat(new StartsWith("UPDATE")), Matchers.<Object>anyVararg());
    }

    @Test
    public void testUpdateRole() {
        User user = new User();
        user.setRole(new Role());
        userDao.updateRole(user);
        verify(jdbcTemplate).update(argThat(new StartsWith("UPDATE")), Matchers.<Object>anyVararg());
    }
    @Test
    public void testUpdatePassword() {
        User user = new User();
        userDao.updatePassword(user);
        verify(jdbcTemplate).update(argThat(new StartsWith("UPDATE")), Matchers.<Object>anyVararg());
    }


    @Test
    public void testDelete() {
        User user = new User();
        userDao.delete(user);
        verify(jdbcTemplate).update(argThat(new StartsWith("DELETE")), eq(user.getId()));
    }
}
