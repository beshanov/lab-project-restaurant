//package com.labproject.restaurant.dao;
//
//import com.labproject.restaurant.dao.impl.OrderStatusDaoImpl;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.internal.matchers.StartsWith;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import static org.mockito.Matchers.anyInt;
//import static org.mockito.Matchers.anyString;
//import static org.mockito.Matchers.argThat;
//import static org.mockito.Matchers.eq;
//import static org.mockito.Mockito.*;
//
//public class OrderStatusDaoImplTest extends OrderStatusDaoImpl {
//    private OrderStatusDaoImpl orderStatusDao;
//    private DataSource mockedDataSource;
//    private Connection mockedConnection;
//    private PreparedStatement mockedPreparedStatement;
//    private ResultSet mockedResultSet;
//
//    @Before
//    public void setUp() throws Exception {
//        mockedDataSource = mock(DataSource.class);
//        mockedConnection = mock(Connection.class);
//        mockedPreparedStatement = mock(PreparedStatement.class);
//        mockedResultSet = mock(ResultSet.class);
//
//        when(mockedDataSource.getConnection()).thenReturn(mockedConnection);
//        when(mockedConnection.prepareStatement(anyString())).thenReturn(mockedPreparedStatement);
//        when(mockedPreparedStatement.executeQuery()).thenReturn(mockedResultSet);
//        when(mockedResultSet.next()).thenReturn(true).thenReturn(false);
//
//        orderStatusDao = new OrderStatusDaoImpl();
//        orderStatusDao.setDataSource(mockedDataSource);
//    }
//
//    @Test
//    public void testGetById() throws SQLException {
//        long id = 1;
//
//        orderStatusDao.getById(id);
//
//        verify(mockedDataSource).getConnection();
//        verify(mockedPreparedStatement).setLong(anyInt(), eq(id));
//        verify(mockedConnection).prepareStatement(argThat(new StartsWith("SELECT")));
//        verify(mockedResultSet).getString("NAME");
//    }
//
//  /*  @Test
//    public void testGetAll() throws SQLException {
//        orderStatusDao.getAll();
//
//        verify(mockedDataSource).getConnection();
//        verify(mockedConnection).prepareStatement(argThat(new StartsWith("SELECT")));
//        verify(mockedResultSet).getString("NAME");
//    }*/
//}