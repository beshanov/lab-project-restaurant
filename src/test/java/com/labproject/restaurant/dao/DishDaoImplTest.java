package com.labproject.restaurant.dao;

import com.labproject.restaurant.dao.impl.DishDaoImpl;
import com.labproject.restaurant.entities.Dish;
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
public class DishDaoImplTest {
    @Mock
    private JdbcTemplate mockJdbcTemplate;
    @Mock
    private SimpleJdbcInsert simpleJdbcInsert;
    @InjectMocks
    DishDaoImpl dishDao;

    @Before
    public void setUp() {
        when(simpleJdbcInsert
                .executeAndReturnKey(any(SqlParameterSource.class)))
                .thenReturn(1L);
    }

    @Test
    public void testInsert() {
        dishDao.insert(new Dish());
        verify(simpleJdbcInsert).executeAndReturnKey(any(SqlParameterSource.class));
    }

    @Test
    public void testUpdate() {
        dishDao.update(new Dish());
        verify(mockJdbcTemplate).update(argThat(new StartsWith("UPDATE")), Matchers.<Object>anyVararg());
    }

    @Test
    public void testDelete() {
        dishDao.updateIsDeleted(new Dish());
        verify(mockJdbcTemplate).update(argThat(new StartsWith("UPDATE")), Matchers.<Object>anyVararg());
    }

    @Test
    public void testGetAll() {
        dishDao.getAll();
        verify(mockJdbcTemplate).query(argThat(new StartsWith("SELECT")), any(RowMapper.class));
    }

    @Test
    public void testGetAvailable() {
        dishDao.getAvailable();
        verify(mockJdbcTemplate).query(argThat(new StartsWith("SELECT")), any(RowMapper.class));
    }

    @Test
    public void testGetById() {
        long id = 1;
        dishDao.getById(id);
        verify(mockJdbcTemplate).query(argThat(new StartsWith("SELECT")), any(RowMapper.class), eq(id));
    }
}