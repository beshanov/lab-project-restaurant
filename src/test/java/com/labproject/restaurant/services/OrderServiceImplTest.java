/*
package com.labproject.restaurant.services;

import com.labproject.restaurant.dao.OrderDao;
import com.labproject.restaurant.dao.OrderStatusDao;
import com.labproject.restaurant.dao.UserDao;
import com.labproject.restaurant.dao.impl.OrderDaoImpl;
import com.labproject.restaurant.dao.impl.OrderStatusDaoImpl;
import com.labproject.restaurant.dao.impl.UserDaoImpl;
import com.labproject.restaurant.entities.Order;
import com.labproject.restaurant.entities.OrderStatus;
import com.labproject.restaurant.entities.User;
import com.labproject.restaurant.services.impl.OrderServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

public class OrderServiceImplTest {
    private OrderDao mockedOrderDao;
    private UserDao mockedUserDao;
    private OrderStatusDao mockedOrderStatusDao;
    private OrderServiceImpl orderService;
    private Order mockedOrder;

    @Before
    public void setUp() {
        mockedOrderDao = mock(OrderDaoImpl.class);
        mockedUserDao = mock(UserDaoImpl.class);
        mockedOrderStatusDao = mock(OrderStatusDaoImpl.class);

        mockedOrder = generateNewOrder();

        when(mockedOrderDao.getAll()).thenReturn(new ArrayList<>());
        when(mockedOrderDao.getById(anyLong())).thenReturn(mockedOrder);
        when(mockedUserDao.getById(anyLong())).thenReturn(mockedOrder.getUser());
        when(mockedOrderStatusDao.getById(anyLong())).thenReturn(mockedOrder.getStatus());

        orderService = new OrderServiceImpl();
        orderService.setOrderDao(mockedOrderDao);
        orderService.setUserDao(mockedUserDao);
        orderService.setOrderStatusDao(mockedOrderStatusDao);
    }

    @Test
    public void testCreateNewOrder() {
        Order order = generateNewOrder();

        orderService.createNewOrder(order);

        verify(mockedOrderDao).insert(order);
    }

    @Test
    public void testGetOrderById() {
        long id = 5;

        orderService.getOrderById(id);

        verify(mockedOrderDao).getById(id);
        verify(mockedOrderStatusDao).getById(anyLong());
        verify(mockedUserDao).getById(anyLong());
    }

    @Test
    public void testGetAllOrders() {
        orderService.getAllOrders();

        verify(mockedOrderDao).getAll();
        verify(mockedOrderStatusDao, atMost(1)).getById(anyLong());
        verify(mockedUserDao, atMost(1)).getById(anyLong());
    }

    @Test
    public void testSetOrder() {
        orderService.setOrder(mockedOrder);

        verify(mockedOrderDao).update(mockedOrder);
    }

    @Test
    public void testDeleteOrderById() {
        long id = 5;

        orderService.deleteOrderById(id);

        verify(mockedOrderDao).deleteById(id);
    }

    private Order generateNewOrder() {
        Order result = new Order();

        result.setId(10L);
        result.setOrderDate(new Timestamp(20L));
        result.setUser(new User());
        result.getUser().setId(20L);
        result.setStatus(new OrderStatus());
        result.getStatus().setId(20L);

        return result;
    }
}*/
