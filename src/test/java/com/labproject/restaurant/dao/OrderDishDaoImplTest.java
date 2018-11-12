package com.labproject.restaurant.dao;

import com.labproject.restaurant.dao.impl.OrderDishDaoImpl;
import com.labproject.restaurant.entities.Dish;
import com.labproject.restaurant.entities.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.StartsWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderDishDaoImplTest {


    @Mock
    private JdbcTemplate mockJdbcTemplate;
    @Mock
    private SimpleJdbcInsert mockSimpleJdbcInsert;
    @InjectMocks
    OrderDishDaoImpl orderDishDao;


    @Test
    public void testAddDishToOrder() {
        int count = 1;
        orderDishDao.addDishToOrder(new Dish(), new Order(), count);
        verify(mockSimpleJdbcInsert).execute(any(SqlParameterSource.class));
    }

    @Test
    public void testGetDishesByOrderId() {
        long id = 1;
        orderDishDao.getDishesByOrderId(id);
        verify(mockJdbcTemplate).queryForList(argThat(new StartsWith("SELECT")), eq(id));
    }

    @Test
    public void testDeleteDishFromOrder() {
        Order order = new Order();
        Dish dish = new Dish();
        orderDishDao.deleteDishFromOrder(dish, order);
        verify(mockJdbcTemplate).update(argThat(new StartsWith("DELETE")), eq(dish.getId()), eq(order.getId()));
    }

    @Test
    public void testDeleteDish() {
        Dish dish = new Dish();
        orderDishDao.deleteDish(dish);
        verify(mockJdbcTemplate).update(argThat(new StartsWith("DELETE")), eq(dish.getId()));
    }
}