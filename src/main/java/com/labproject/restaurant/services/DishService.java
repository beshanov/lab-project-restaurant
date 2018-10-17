package com.labproject.restaurant.services;

import com.labproject.restaurant.entities.DishEntity;

import java.util.List;

public interface DishService {
    DishEntity getById(long id);
    List<DishEntity> getAll();
    void insert(DishEntity dishEntity);
    void update(DishEntity dishEntity);
    void delete(DishEntity dishEntity);
}
