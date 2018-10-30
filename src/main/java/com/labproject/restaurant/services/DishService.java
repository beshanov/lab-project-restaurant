package com.labproject.restaurant.services;

import com.labproject.restaurant.entities.Dish;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface DishService {

    Dish getById(long id);

    List<Dish> getAll();

    Map<Dish, Integer> getAllByOrderId(long orderId);

    Map<Dish, Integer> addToDishMap(HttpServletRequest request);

    Map<Dish, Integer> deleteFromDishMap(HttpServletRequest request);

    void insert(Dish dish);

    void update(Dish dish);

    void delete(Dish dish);
}
