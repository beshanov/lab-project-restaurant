package com.labproject.restaurant.services;

import com.labproject.restaurant.entities.Dish;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * A service layer interface that provides the CRUD functionality
 * for the {@link Dish} entity
 *
 * @author Igor Pevtsov
 * @author Alexandr Zorin
 * @author Vsevolod Beshanov
 * @author Zhanna Fedorova
 * @see Dish
 */
public interface DishService {

    /**
     * Returns a {@link Dish} object from a data storage
     * by the specified id
     *
     * @param id a primary key of Dish table
     * @return the Dish object at the specified id
     * @see {@link Dish}
     */
    Dish getById(long id);

    /**
     * Returns a List of {@link Dish} objects from a data storage
     *
     * @return the list of Dish objects
     * @see {@link Dish}
     */
    List<Dish> getAll();

    /**
     * Returns a List of {@link Dish} objects from a data storage
     *
     * @param page page number
     * @param countPerPage number of dishes on page
     * @param deleted is dish deleted
     * @return the list of Dish objects
     * @see {@link Dish}
     */
    List<Dish> getPage(int page,int countPerPage, boolean deleted);

    /**
     * Returns a List of {@link Dish} objects from a data storage
     *
     * @param page page number
     * @param countPerPage number of dishes on page
     * @param deleted is dish deleted
     * @return the list of Dish objects
     * @see {@link Dish}
     */
    Map<Dish, Integer> getAllByOrderId(long orderId);

    Map<Dish, Integer> addToDishMap(HttpServletRequest request);

    Map<Dish, Integer> deleteFromDishMap(HttpSession session, long dishId);

    /**
     * Inserts the {@link Dish} object into a data storage
     *
     * @param dish the Dish entity
     * @see Dish
     */
    void insert(Dish dish);

    void update(Dish dish);

    void updateIsDeleted(Dish dish);

    int dishesCount(boolean deleted);
}
