package com.labproject.restaurant.dao;

import com.labproject.restaurant.entities.DishEntity;

import java.util.List;

public interface DishDao {
    public void save(DishEntity dishEntity);
    public void update(DishEntity dishEntity);
    public void delete(DishEntity dishEntity);
    public List<DishEntity> findAll();
    public DishEntity findById(long id);
}
