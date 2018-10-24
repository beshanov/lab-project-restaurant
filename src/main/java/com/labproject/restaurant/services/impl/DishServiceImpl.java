package com.labproject.restaurant.services.impl;

import com.labproject.restaurant.dao.DishDao;
import com.labproject.restaurant.entities.Dish;
import com.labproject.restaurant.services.DishService;

import java.util.List;

public class DishServiceImpl implements DishService {

    private DishDao dishDao;

    public void setDishDao(DishDao dishDao) { this.dishDao = dishDao; }

    @Override
    public Dish getById(long id) { return dishDao.getById(id); }

    @Override
    public List<Dish> getAll() { return dishDao.getAll(); }

    @Override
    public void insert(Dish dish) { dishDao.insert(dish); }

    @Override
    public void update(Dish dish) { dishDao.update(dish); }

    @Override
    public void delete(Dish dish) { dishDao.delete(dish); }
}
