package com.labproject.restaurant.services;

import com.labproject.restaurant.entities.Dish;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface DishService {

    Dish getById(long id);

    List<Dish> getAll();

    List<Dish> getPage(int page,int countPerPage, boolean deleted);

    Map<Dish, Integer> getAllByOrderId(long orderId);

    Map<Dish, Integer> addToDishMap(HttpServletRequest request);

    Map<Dish, Integer> deleteFromDishMap(HttpSession session, long dishId);

    void insert(Dish dish);

    void update(Dish dish);

    void updateIsDeleted(Dish dish);

    int dishesCount(boolean deleted);
}
