package com.labproject.restaurant.services.impl;

import com.labproject.restaurant.dao.OrderDao;
import com.labproject.restaurant.dao.OrderDishDao;
import com.labproject.restaurant.dao.OrderStatusDao;
import com.labproject.restaurant.dao.UserDao;
import com.labproject.restaurant.entities.Dish;
import com.labproject.restaurant.entities.Order;
import com.labproject.restaurant.entities.OrderStatus;
import com.labproject.restaurant.entities.User;
import com.labproject.restaurant.services.OrderService;
import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class);
    private UserDao userDao;
    private OrderDao orderDao;
    private OrderStatusDao orderStatusDao;
    private OrderDishDao orderDishDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void setOrderStatusDao(OrderStatusDao orderStatusDao) {
        this.orderStatusDao = orderStatusDao;
    }

    public void setOrderDishDao(OrderDishDao orderDishDao) {
        this.orderDishDao = orderDishDao;
    }

    @Override
    public void createNewOrder(Order order) {
        if (order == null) {
            LOGGER.error("Error while creating order: null argument");
            return;
        }

        orderDao.insert(order);
    }

    @Override
    public Order getOrderById(long orderId) {
        if (orderId < 1) {
            LOGGER.error("Error while getting order: orderId < 1");
            return new Order();
        }

        Order result = orderDao.getById(orderId);
        OrderStatus status = orderStatusDao.getById(result.getStatus().getId());
        User user = userDao.getById(result.getUser().getId());
        result.setStatus(status);
        result.setUser(user);

        return result;
    }

    @Override
    public void createOrderWithDishes(long userId, Map<Dish, Integer> dishMap) {
        if (dishMap == null) {
            LOGGER.error("Error while getting order: orderId < 1");
            return;
        }

        Order order = new Order();
        order.setStatus(orderStatusDao.getById(1));
        order.setUser(userDao.getById(userId));
        order.setOrderDate(Timestamp.from(Instant.now()));

        for (Map.Entry<Dish, Integer> entry : dishMap.entrySet()) {
            orderDishDao.addDishToOrder(entry.getKey(), order, entry.getValue());
        }
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> result = orderDao.getAll();

        for (Order o : result) {
            o.setStatus(orderStatusDao.getById(o.getStatus().getId()));
            o.setUser(userDao.getById(o.getUser().getId()));
        }

        return result;
    }

    @Override
    public void setOrder(Order order) {
        if (order == null) {
            LOGGER.error("Error while modifying order: null argument");
            return;
        }

        orderDao.update(order);
    }

    @Override
    public void deleteOrderById(long orderId) {
        if (orderId < 1) {
            LOGGER.error("Error while deleting order: orderId < 1");
        }

        orderDao.deleteById(orderId);
    }
}
