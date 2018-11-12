/*
package com.labproject.restaurant.test.dao;

import com.labproject.restaurant.dao.impl.UserDaoImpl;
import com.labproject.restaurant.entities.Role;
import com.labproject.restaurant.entities.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.matchers.StartsWith;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class UserDaoImplTest {
    private DataSource mockDataSource;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;
    private UserDaoImpl userDao;

    private long id = 1;
    private String login = "login";
    private String firstname = "firstname";
    private String lastname = "lastname";
    private String password = "password";


    @Before
    public void setUp() throws Exception {
        mockDataSource = mock(DataSource.class);
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);
        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockConnection.prepareStatement(anyString(), anyInt())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockPreparedStatement.getGeneratedKeys()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        userDao = new UserDaoImpl();
        userDao.setDataSource(mockDataSource);
    }

    @Test
    public void testGetById() throws SQLException {
        long id = 1;
        userDao.getById(id);
        verify(mockDataSource).getConnection();
        verify(mockPreparedStatement).setLong(anyInt(), eq(id));
        verify(mockConnection).prepareStatement(argThat(new StartsWith("SELECT")));
        verify(mockResultSet).getString("firstname");
        verify(mockResultSet).getString("lastname");
        verify(mockResultSet).getString("login");
        verify(mockResultSet).getString("password");
        verify(mockResultSet).getLong("roleid");
    }

    @Test
    public void testInsert() throws SQLException {
        userDao.insert(getNewUser());
        verify(mockDataSource).getConnection();
        verify(mockPreparedStatement).setString(anyInt(), eq(login));
        verify(mockPreparedStatement).setString(anyInt(), eq(firstname));
        verify(mockPreparedStatement).setString(anyInt(), eq(lastname));
        verify(mockPreparedStatement).setString(anyInt(), eq(password));
        verify(mockConnection).prepareStatement(argThat(new StartsWith("INSERT")),anyInt());


    }

    @Test
    public void testUpdate() throws SQLException {
        userDao.updateDetails(getNewUser());
        verify(mockDataSource).getConnection();
        verify(mockPreparedStatement).setString(anyInt(), eq(login));
        verify(mockPreparedStatement).setString(anyInt(), eq(firstname));
        verify(mockPreparedStatement).setString(anyInt(), eq(lastname));
        verify(mockPreparedStatement).setString(anyInt(), eq(password));
        verify(mockPreparedStatement).setLong(anyInt(),eq(id));
        verify(mockConnection).prepareStatement(argThat(new StartsWith("UPDATE")));
    }

    @Test
    public void testDelete() throws SQLException {
        userDao.delete(getNewUser());
        verify(mockDataSource).getConnection();
        verify(mockPreparedStatement).setLong(anyInt(),eq(id));
        verify(mockConnection).prepareStatement(argThat(new StartsWith("DELETE")));
    }

    private User getNewUser() {
        User user = new User();
        user.setId(id);
        user.setLogin(login);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setPassword(password);
        Role mockRole = mock(Role.class);
        when(mockRole.getId()).thenReturn(2L);
        user.setRole(mockRole);
        return user;
    }
}*/
