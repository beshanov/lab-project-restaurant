package com.labproject.restaurant.dao;

import com.labproject.restaurant.dao.impl.RoleDaoImpl;
import com.labproject.restaurant.entities.Role;
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
public class RoleDaoImplTest {

    @Mock
    private JdbcTemplate mockJdbcTemplate;
    @Mock
    private SimpleJdbcInsert mockSimpleJdbcInsert;
    @InjectMocks
    RoleDaoImpl roleDao;

    @Before
    public void setUp() {
        when(mockSimpleJdbcInsert.executeAndReturnKey(any(SqlParameterSource.class))).thenReturn(new Long(1));
    }

    @Test
    public void testGetById() {
        long id = 1;
        roleDao.getById(id);
        verify(mockJdbcTemplate).query(argThat(new StartsWith("SELECT")), any(RowMapper.class), eq(id));
    }

    @Test
    public void testGetRoleByLogin() {
        String login = "login";
        roleDao.getRoleByLogin(login);
        verify(mockJdbcTemplate).query(argThat(new StartsWith("SELECT")), any(RowMapper.class), eq(login));
    }

    @Test
    public void testGetAllRoles() {
        roleDao.getAllRoles();
        verify(mockJdbcTemplate).query(argThat(new StartsWith("SELECT")), any(RowMapper.class));
    }

    @Test
    public void testInsert() {
        roleDao.insert(new Role());
        verify(mockSimpleJdbcInsert).executeAndReturnKey(any(SqlParameterSource.class));
    }

    @Test
    public void testUpdate() {
        roleDao.update(new Role());
        verify(mockJdbcTemplate).update(argThat(new StartsWith("UPDATE")), Matchers.<Object>anyVararg());
    }

    @Test
    public void testDelete() {
        roleDao.delete(new Role());
        verify(mockJdbcTemplate).update(argThat(new StartsWith("DELETE")), anyLong());
    }
}