package com.labproject.restaurant.dao;

import com.labproject.restaurant.entities.DishEntity;

import java.util.List;

public interface DishDao {
    void insert(DishEntity dishEntity);
    void update(DishEntity dishEntity);
    void delete(DishEntity dishEntity);
    List<DishEntity> getAll();
    DishEntity getById(long id);
}
