package com.labproject.restaurant.dao.impl;

import com.labproject.restaurant.entities.Bill;
import com.labproject.restaurant.entities.Order;
import com.labproject.restaurant.entities.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.matchers.StartsWith;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class BillDaoImplTest {
    private DataSource ds;

    private Connection c;

    private PreparedStatement ps;

    private ResultSet rs;

    private BillDaoImpl billDao;

    private long time;

    @Before
    public void setUp() throws Exception {
        ds = mock(DataSource.class);
        c = mock(Connection.class);
        rs = mock(ResultSet.class);
        ps = mock(PreparedStatement.class);
        time = System.currentTimeMillis();

        when(ds.getConnection()).thenReturn(c);
        when(c.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true).thenReturn(false);

        when(rs.getLong("id")).thenReturn((long) 1);
        when(rs.getLong("OrderId")).thenReturn((long) 1);
        when(rs.getLong("AdminId")).thenReturn((long) 2);
        when(rs.getBigDecimal("Amount")).thenReturn(new BigDecimal(12));
        when(rs.getTimestamp("Date")).thenReturn(new Timestamp(time));
        when(rs.getBoolean("Status")).thenReturn(true);

        billDao = new BillDaoImpl();
        billDao.setDataSource(ds);
    }

    @Test
    public void testGetById() throws SQLException {
        long id = 1;
        billDao.getById(id);
        verify(ds, times(1)).getConnection();
        verify(ps, times(1)).setLong(anyInt(), eq(id));
        verify(c, times(1)).prepareStatement(anyString());
        verify(rs, times(1)).next();
        verify(rs, times(1)).getLong("id");
        verify(rs, times(1)).getLong("OrderId");
        verify(rs, times(1)).getLong("AdminId");
        verify(rs, times(1)).getBigDecimal("Amount");
        verify(rs, times(1)).getTimestamp("Date");
        verify(rs, times(1)).getBoolean("Status");
    }

    @Test
    public void testInsert() throws SQLException {
        Bill bill = new Bill();
        bill.setId(1);
        bill.setStatus(true);
        bill.setDate(new Timestamp(time));
        User admin = new User();
        admin.setId(2);
        bill.setAdmin(admin);
        Order order = new Order();
        order.setId(1);
        bill.setOrder(order);
        bill.setAmount(new BigDecimal(12));

        billDao.insert(bill);

        verify(ds, times(1)).getConnection();
        verify(c, times(1)).prepareStatement(anyString());
        verify(ps, times(3)).setLong(anyInt(), anyLong());
        verify(ps).setBigDecimal(anyInt(), eq(bill.getAmount()));
        verify(ps).setTimestamp(anyInt(), eq(bill.getDate()));
        verify(ps).setBoolean(anyInt(), eq(bill.getStatus()));
    }

    @Test
    public void testDeleteById() throws SQLException {
        long id = 1;
        billDao.deleteById(id);
        verify(ds).getConnection();
        verify(ps).setLong(anyInt(), eq(id));
        verify(c).prepareStatement(argThat(new StartsWith("DELETE")));
    }


}