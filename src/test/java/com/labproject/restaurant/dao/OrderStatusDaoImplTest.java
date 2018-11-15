package com.labproject.restaurant.dao;


import com.labproject.restaurant.dao.impl.OrderStatusDaoImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.StartsWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderStatusDaoImplTest {

    @Mock
    JdbcTemplate mockJdbcTemplate;
    @Mock
    SimpleJdbcInsert mockSimpleJdbcInsert;

    @InjectMocks
    OrderStatusDaoImpl orderStatusDao;


    @Before
    public void setUp() {
        when(mockSimpleJdbcInsert.executeAndReturnKey(any(SqlParameterSource.class))).thenReturn(1L);
    }

    @Test
    public void testGetById() {
        long id = 1;
        orderStatusDao.getById(id);
        verify(mockJdbcTemplate).query(argThat(new StartsWith("SELECT")), any(RowMapper.class), eq(id));
    }

    @Test
    public void testGetAll() {
        orderStatusDao.getAll();
        verify(mockJdbcTemplate).query(argThat(new StartsWith("SELECT")), any(RowMapper.class));
    }
}