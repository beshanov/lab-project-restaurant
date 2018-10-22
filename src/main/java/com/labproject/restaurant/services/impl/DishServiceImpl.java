package com.labproject.restaurant.services.impl;

import com.labproject.restaurant.dao.DishDao;
import com.labproject.restaurant.entities.DishEntity;
import com.labproject.restaurant.services.DishService;

import java.util.List;

public class DishServiceImpl implements DishService {

    private DishDao dishDao;

    public void setDishDao(DishDao dishDao) { this.dishDao = dishDao; }

    @Override
    public DishEntity getById(long id) { return dishDao.getById(id); }

    @Override
    public List<DishEntity> getAll() { return dishDao.getAll(); }

    @Override
    public void insert(DishEntity dishEntity) { dishDao.insert(dishEntity); }

    @Override
    public void update(DishEntity dishEntity) { dishDao.update(dishEntity); }

    @Override
    public void delete(DishEntity dishEntity) { dishDao.delete(dishEntity); }
}
