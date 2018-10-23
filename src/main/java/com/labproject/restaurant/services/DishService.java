package com.labproject.restaurant.services;

import com.labproject.restaurant.entities.Dish;

import java.util.List;

public interface DishService {
    Dish getById(long id);
    List<Dish> getAll();
    void insert(Dish dish);
    void update(Dish dish);
    void delete(Dish dish);
}
