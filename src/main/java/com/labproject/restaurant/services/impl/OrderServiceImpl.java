package com.labproject.restaurant.services.impl;

import com.labproject.restaurant.dao.OrderDao;
import com.labproject.restaurant.dao.OrderStatusDao;
import com.labproject.restaurant.dao.UserDao;
import com.labproject.restaurant.entities.Order;
import com.labproject.restaurant.entities.OrderStatus;
import com.labproject.restaurant.entities.User;
import com.labproject.restaurant.services.OrderService;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final Logger logger = Logger.getLogger(OrderServiceImpl.class);
    private UserDao userDao;
    private OrderDao orderDao;
    private OrderStatusDao orderStatusDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void setOrderStatusDao(OrderStatusDao orderStatusDao) {
        this.orderStatusDao = orderStatusDao;
    }

    @Override
    public void createNewOrder(Order order) {
        if (order == null) {
            logger.error("Error while creating order: null argument");
            return;
        }

        orderDao.insert(order);
    }

    @Override
    public Order getOrderById(long orderId) {
        if (orderId < 1) {
            logger.error("Error while getting order: orderId < 1");
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
    public List<Order> getAllOrders() {
        return orderDao.getAll();
    }

    @Override
    public List<Order> getOrdersByUserId(long userId) {
        if (userId < 1) {
            logger.error("Error while getting order by userId: userId < 1");
            return new ArrayList<>();
        }

        return orderDao.getAllByUserId(userId);
    }

    @Override
    public List<Order> getOrdersByStatusId(long statusId) {
        if (statusId < 1) {
            logger.error("Error while getting order by statusId: statusId < 1");
            return new ArrayList<>();
        }

        return orderDao.getAllByStatusId(statusId);
    }

    @Override
    public void setOrder(Order order) {
        if (order == null) {
            logger.error("Error while modifying order: null argument");
            return;
        }

        orderDao.update(order);
    }

    @Override
    public void deleteOrderById(long orderId) {
        if (orderId < 1) {
            logger.error("Error while deleting order: orderId < 1");
        }

        orderDao.deleteById(orderId);
    }
}
