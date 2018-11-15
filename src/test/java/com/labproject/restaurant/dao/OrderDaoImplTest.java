package com.labproject.restaurant.dao;


import com.labproject.restaurant.dao.impl.OrderDaoImpl;
import com.labproject.restaurant.entities.Order;
import com.labproject.restaurant.entities.OrderStatus;
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

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderDaoImplTest {

    @Mock
    private JdbcTemplate mockJdbcTemplate;
    @Mock
    private SimpleJdbcInsert mockSimpleJdbcInsert;
    @InjectMocks
    OrderDaoImpl orderDao;


    @Before
    public void setUp() {
        when(mockSimpleJdbcInsert.executeAndReturnKey(any(SqlParameterSource.class))).thenReturn(1L);
    }

    @Test
    public void testInsert() {
        Order order = new Order();
        order.setUser(new User());
        order.setStatus(new OrderStatus());
        orderDao.insert(order);
        verify(mockSimpleJdbcInsert).executeAndReturnKey(any(SqlParameterSource.class));
    }

    @Test
    public void testGetById() {
        long id = 1;
        orderDao.getById(id);
        verify(mockJdbcTemplate).query(argThat(new StartsWith("SELECT")), any(RowMapper.class), eq(id));
    }

    @Test
    public void testGetAll() {
        orderDao.getAll();
        verify(mockJdbcTemplate).query(argThat(new StartsWith("SELECT")), any(RowMapper.class));

    }

    public void testGetAllByUserId() {
        long id = 1;
        orderDao.getAllByUserId(id);
        verify(mockJdbcTemplate).query(argThat(new StartsWith("SELECT")), any(RowMapper.class), eq(id));
    }

    @Test
    public void testUpdate() {
        Order order = new Order();
        order.setUser(new User());
        order.setStatus(new OrderStatus());
        order.setAdmin(new User());
        orderDao.update(order);
        verify(mockJdbcTemplate).update(argThat(new StartsWith("UPDATE")), Matchers.<Object>anyVararg());
    }

    @Test
    public void testDeleteById() {
        long id = 1;
        orderDao.deleteById(id);
        verify(mockJdbcTemplate).update(argThat(new StartsWith("DELETE")), eq(id));
    }
}
