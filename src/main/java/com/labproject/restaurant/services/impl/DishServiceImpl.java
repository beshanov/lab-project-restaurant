package com.labproject.restaurant.services.impl;

import com.labproject.restaurant.dao.DishDao;
import com.labproject.restaurant.dao.OrderDishDao;
import com.labproject.restaurant.entities.Dish;
import com.labproject.restaurant.entities.User;
import com.labproject.restaurant.services.DishService;
import com.labproject.restaurant.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishDao dishDao;

    @Autowired
    private OrderDishDao orderDishDao;

    @Autowired
    private UserService userService;

    @Override
    public Dish getById(long id) {
        return dishDao.getById(id);
    }

    @Override
    public List<Dish> getAll() {
        User loggedUser = userService.getLoggedUser();

        if (loggedUser != null && loggedUser.getRole().getId() == 1L) {
            return dishDao.getAll();
        }
        return dishDao.getAvailable();
    }

    @Override
    public List<Dish> getPage(int page,int countPerPage, boolean deleted) {
       return dishDao.getPage(page,countPerPage,deleted);
    }

    @Override
    public Map<Dish, Integer> getAllByOrderId(long orderId) {
        if (orderId < 1) {
            return new HashMap<>();
        }

        Map<Dish, Integer> result = new HashMap<>();
        for (Map.Entry<Dish, Integer> entry : orderDishDao.getDishesByOrderId(orderId).entrySet()) {
            result.put(dishDao.getById(entry.getKey().getId()), entry.getValue());
        }

        return result;
    }

    @Override
    public Map<Dish, Integer> addToDishMap(HttpServletRequest request) {
        long dishId = Long.valueOf(request.getParameter("id"));
        int count = Integer.valueOf(request.getParameter("count"));
        Dish dish = getById(dishId);

        Object objMap = request.getSession().getAttribute("dishMap");
        Map<Dish, Integer> dishMap;

        if (objMap == null) {
            dishMap = new HashMap<>();
        } else {
            dishMap = (Map<Dish, Integer>) objMap;
        }

        if (count < 1) {
            return dishMap;
        }

        if (dishMap.containsKey(dish)) {
            dishMap.put(dish, dishMap.get(dish) + count);
        } else {
            dishMap.put(dish, count);
        }

        return dishMap;
    }

    @Override
    public Map<Dish, Integer> deleteFromDishMap(HttpSession session, long dishId) {
        Object objMap = session.getAttribute("dishMap");

        if (objMap == null) {
            return new HashMap<>();
        }

        Map<Dish, Integer> dishMap = (Map<Dish, Integer>) objMap;

        if (dishId < 1) {
            return dishMap;
        }

        for (Map.Entry<Dish, Integer> entry : dishMap.entrySet()) {
            if (entry.getKey().getId() == dishId) {
                dishMap.remove(entry.getKey());
                break;
            }
        }

        return dishMap;
    }

    @Override
    public void insert(Dish dish) {
        dishDao.insert(dish);
    }

    @Override
    public void update(Dish dish) {
        dishDao.update(dish);
    }

    @Override
    public void updateIsDeleted(Dish dish) {
        dish.setDeleted(!dish.isDeleted());
        dishDao.updateIsDeleted(dish);
    }

    @Override
    public int dishesCount(boolean deleted) {
       return dishDao.dishesCount(deleted);
    }
}
