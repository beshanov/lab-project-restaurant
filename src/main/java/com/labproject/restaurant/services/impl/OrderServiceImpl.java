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
import com.labproject.restaurant.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
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

    @Autowired
    private UserService userService;

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
    public void createOrderWithDishes(Map<Dish, Integer> dishMap) {
        User loggedUser = userService.getLoggedUser();

        if (loggedUser == null || loggedUser.getId() == 0) {
            LOGGER.error("Error while getting users orders: no such user");
            return;
        }

        Order order = new Order();
        order.setStatus(orderStatusDao.getById(1));
        order.setUser(loggedUser);
        order.setOrderDate(Timestamp.from(Instant.now()));
        orderDao.insert(order);

        for (Map.Entry<Dish, Integer> entry : dishMap.entrySet()) {
            orderDishDao.addDishToOrder(entry.getKey(), order, entry.getValue());
        }
    }

    @Override
    public List<Order> getOrdersByUser() {
        User loggedUser = userService.getLoggedUser();

        if (loggedUser == null || loggedUser.getId() == 0) {
            LOGGER.error("Error while getting users orders: no such user");
            return new ArrayList<>();
        }

        List<Order> result;

        if (loggedUser.getRole().getId() == 1L) {
            result = orderDao.getAll();
        } else {
            result = orderDao.getAllByUserId(loggedUser.getId());
        }

        for (Order order : result) {
            order.setStatus(orderStatusDao.getById(order.getStatus().getId()));
            order.setUser(userDao.getById(order.getUser().getId()));
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

    @Override
    public void modifyOrderStatus(long orderId, long statusId) {
        if (orderId < 1 || statusId < 1) {
            LOGGER.error("Error while modifying order status: orderId < 1 or statusId < 1");
            return;
        }

        OrderStatus status = orderStatusDao.getById(statusId + 1);
        if (status == null || status.getId() == 0) {
            LOGGER.error("Error while modifying order status: no such status");
            return;
        }

        Order order = orderDao.getById(orderId);
        if (order == null || order.getId() == 0) {
            LOGGER.error("Error while modifying order status: no such order");
            return;
        }

        order.setStatus(status);
        orderDao.update(order);
    }
}
