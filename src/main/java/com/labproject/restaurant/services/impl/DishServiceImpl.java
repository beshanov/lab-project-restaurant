package com.labproject.restaurant.services.impl;

import com.labproject.restaurant.dao.DishDao;
import com.labproject.restaurant.entities.Dish;
import com.labproject.restaurant.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishDao dishDao;

    @Override
    public Dish getById(long id) {
        return dishDao.getById(id);
    }

    @Override
    public List<Dish> getAll() {
        return dishDao.getAll();
    }

    @Override
    public Map<Dish, Integer> addToDishMap(HttpServletRequest request) {
        long dishId = Long.valueOf(request.getParameter("id"));
        int count = Integer.valueOf(request.getParameter("count"));
        Dish dish = getById(dishId);

        Object objMap = request.getSession().getAttribute("dishMap");
        if (objMap == null) {
            return new HashMap<>();
        }

        Map<Dish, Integer> dishMap = (Map<Dish, Integer>) objMap;

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
    public Map<Dish, Integer> deleteFromDishMap(HttpServletRequest request) {
        long deleteId = Long.valueOf(request.getParameter("id"));
        Object objMap = request.getSession().getAttribute("dishMap");

        if (objMap == null) {
            return new HashMap<>();
        }

        Map<Dish, Integer> dishMap = (Map<Dish, Integer>) objMap;

        if (deleteId < 1) {
            return dishMap;
        }

        for (Map.Entry<Dish, Integer> entry : dishMap.entrySet()) {
            if (entry.getKey().getId() == deleteId) {
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
    public void delete(Dish dish) {
        dishDao.delete(dish);
    }
}
