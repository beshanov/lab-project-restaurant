/*
package com.labproject.restaurant.dao;

import com.labproject.restaurant.dao.impl.OrderDaoImpl;
import com.labproject.restaurant.entities.Order;
import com.labproject.restaurant.entities.OrderStatus;
import com.labproject.restaurant.entities.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.matchers.StartsWith;

import javax.sql.DataSource;
import java.sql.*;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.*;

public class OrderDaoImplTest {
    private OrderDaoImpl orderDao;
    private DataSource mockedDataSource;
    private Connection mockedConnection;
    private PreparedStatement mockedPreparedStatement;
    private ResultSet mockedResultSet;

    @Before
    public void setUp() throws Exception {
        mockedDataSource = mock(DataSource.class);
        mockedConnection = mock(Connection.class);
        mockedPreparedStatement = mock(PreparedStatement.class);
        mockedResultSet = mock(ResultSet.class);

        when(mockedDataSource.getConnection()).thenReturn(mockedConnection);
        when(mockedConnection.prepareStatement(anyString())).thenReturn(mockedPreparedStatement);
        when(mockedPreparedStatement.executeQuery()).thenReturn(mockedResultSet);
        when(mockedResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockedResultSet.getLong(anyString())).thenReturn(5L);
        when(mockedResultSet.getString(anyString())).thenReturn("");
        when(mockedResultSet.getTimestamp(anyString())).thenReturn(new Timestamp(10L));

        orderDao = new OrderDaoImpl();
        orderDao.setDataSource(mockedDataSource);
    }

    @Test
    public void testGetById() throws SQLException {
        long id = 1;

        orderDao.getById(id);

        verify(mockedDataSource).getConnection();
        verify(mockedPreparedStatement).setLong(anyInt(), eq(id));
        verify(mockedConnection).prepareStatement(argThat(new StartsWith("SELECT")));
        verify(mockedResultSet).getTimestamp("ORDERDATE");
        verify(mockedResultSet).getLong("USERID");
        verify(mockedResultSet).getLong("STATUSID");
    }

    @Test
    public void testInsert() throws SQLException {
        Order order = generateNewOrder();

        orderDao.insert(order);

        verify(mockedDataSource).getConnection();
        verify(mockedConnection).prepareStatement(argThat(new StartsWith("INSERT")));
        verify(mockedPreparedStatement).setTimestamp(anyInt(), eq(order.getOrderDate()));
        verify(mockedPreparedStatement, times(2)).setLong(anyInt(), anyLong());
    }

    @Test
    public void testGetAll() throws SQLException {
        orderDao.getAll();

        verify(mockedDataSource).getConnection();
        verify(mockedConnection).prepareStatement(argThat(new StartsWith("SELECT")));
        verify(mockedResultSet).getTimestamp("ORDERDATE");
        verify(mockedResultSet, times(3)).getLong(anyString());
    }

    @Test
    public void testUpdate() throws SQLException {
        Order order = generateNewOrder();

        orderDao.updateDetails(order);

        verify(mockedDataSource).getConnection();
        verify(mockedConnection).prepareStatement(argThat(new StartsWith("UPDATE")));
        verify(mockedPreparedStatement).setTimestamp(anyInt(), eq(order.getOrderDate()));
        verify(mockedPreparedStatement, times(3)).setLong(anyInt(), anyLong());
    }

    @Test
    public void testDeleteById() throws SQLException {
        long id = 1;

        orderDao.deleteById(id);

        verify(mockedDataSource).getConnection();
        verify(mockedConnection).prepareStatement(argThat(new StartsWith("DELETE")));
        verify(mockedPreparedStatement).setLong(anyInt(), eq(id));
    }

    private Order generateNewOrder() {
        Order result = new Order();

        result.setId(5L);
        result.setOrderDate(new Timestamp(10L));
        result.setUser(new User());
        result.getUser().setId(10L);
        result.setStatus(new OrderStatus());
        result.getStatus().setId(10L);

        return result;
    }
}*/
