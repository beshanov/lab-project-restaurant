/*
package com.labproject.restaurant.test.dao;

import com.labproject.restaurant.dao.impl.RoleDaoImpl;
import com.labproject.restaurant.entities.Role;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.matchers.StartsWith;
import javax.sql.DataSource;
import java.sql.*;

import static org.mockito.Mockito.*;

public class RoleDaoImplTest {
    private DataSource mockDataSource;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;
    RoleDaoImpl roleDao;

    private long id = 1;
    private String name = "name";

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
        roleDao = new RoleDaoImpl();
        roleDao.setDataSource(mockDataSource);
    }

    @Test
    public void testGetByIdTest() throws SQLException {
        roleDao.getById(id);
        verify(mockDataSource).getConnection();
        verify(mockPreparedStatement).setLong(anyInt(), eq(id));
        verify(mockConnection).prepareStatement(argThat(new StartsWith("SELECT")));
        verify(mockResultSet).getString("name");

    }

    @Test
    public void testInsert() throws SQLException {
        roleDao.insert(getNewRole());
        verify(mockDataSource).getConnection();
        verify(mockPreparedStatement).setString(anyInt(), eq(name));
    }

    @Test
    public void testUpdate() throws SQLException {
        roleDao.updateWithoutPasswordAndRole(getNewRole());
        verify(mockDataSource).getConnection();
        verify(mockPreparedStatement).setLong(anyInt(), eq(id));
        verify(mockPreparedStatement).setString(anyInt(), eq(name));
        verify(mockConnection).prepareStatement(argThat(new StartsWith("UPDATE ")));
    }

    @Test
    public void testDelete() throws SQLException {
        roleDao.delete(getNewRole());
        verify(mockDataSource).getConnection();
        verify(mockPreparedStatement).setLong(anyInt(), eq(id));
        verify(mockConnection).prepareStatement(argThat(new StartsWith("DELETE ")));
    }

    private Role getNewRole() {
        Role role = new Role();
        role.setId(id);
        role.setName(name);
        return role;
    }
}*/
