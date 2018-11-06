package com.labproject.restaurant.dao;

import com.labproject.restaurant.entities.Dish;

import java.util.List;

public interface DishDao {

    void insert(Dish dish);

    void update(Dish dish);

    void delete(Dish dish);

    List<Dish> getAll();

    List<Dish> getAvailable();

    Dish getById(long id);
}
