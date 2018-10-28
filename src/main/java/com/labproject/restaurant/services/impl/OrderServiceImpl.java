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
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderStatusDao orderStatusDao;

    @Autowired
    private OrderDishDao orderDishDao;

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
    public void createOrderWithDishes(Object objUser, Object objDishMap) {
        Map<Dish, Integer> dishMap;
        User user;

        if (objDishMap != null && objUser != null) {
            dishMap = (Map<Dish, Integer>) objDishMap;
            user = (User) objUser;
        } else {
            LOGGER.error("Error while getting order: null arguments");
            return;
        }

        Order order = new Order();
        order.setStatus(orderStatusDao.getById(1));
        order.setUser(user);
        order.setOrderDate(Timestamp.from(Instant.now()));
        orderDao.insert(order);

        for (Map.Entry<Dish, Integer> entry : dishMap.entrySet()) {
            orderDishDao.addDishToOrder(entry.getKey(), order, entry.getValue());
        }
    }

    @Override
    public Map<Order, List<Dish>> getAllOrdersWithDishes() {
        Map<Order, List<Dish>> result = new HashMap<>();

        for (Order order : orderDao.getAll()) {
            order.setStatus(orderStatusDao.getById(order.getStatus().getId()));
            order.setUser(userDao.getById(order.getUser().getId()));
            result.put(order, orderDishDao.getDishesByOrder(order));
        }

        return result;
    }

    @Override
    public Map<Order, List<Dish>> getOrdersWithDishesByUser(Object objUser) {
        User user;

        if (objUser != null) {
            user = (User) objUser;
        } else {
            LOGGER.error("Error while getting users orders: null user");
            return new HashMap<>();
        }

        if (user.getRole().getId() == 1L) {
            return getAllOrdersWithDishes();
        }

        Map<Order, List<Dish>> result = new HashMap<>();
        for (Order order : orderDao.getAllByUserId(user.getId())) {
            result.put(order, orderDishDao.getDishesByOrder(order));
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
            return;
        }

        orderDao.deleteById(orderId);
    }
}
