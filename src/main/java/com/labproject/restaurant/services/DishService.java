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
     * @param page         page number
     * @param countPerPage number of dishes on page
     * @param deleted      is dish deleted
     * @return the list of Dish objects
     * @see {@link Dish}
     */
    List<Dish> getPage(int page, int countPerPage, boolean deleted);

    /**
     * Returns a Map of {@link Dish} objects from a data storage
     * by the specified orderId
     *
     * @param orderId is dish deleted
     * @return the map of Dish objects
     * @see {@link Dish}
     */
    Map<Dish, Integer> getAllByOrderId(long orderId);

    /**
     * Adds a {@link Dish} object to a {@link Map}
     * and saves it to the session attribute
     *
     * @param request HttpServletRequest
     * @return the map of Dish objects
     * @see {@link Dish}
     */
    Map<Dish, Integer> addToDishMap(HttpServletRequest request);

    /**
     * Deletes a {@link Dish} object specified by dishId
     * from the session attribute
     *
     * @param session HttpSession
     * @param dishId  a primary key of Dish table
     * @return the map of Dish objects
     * @see {@link Dish}
     */
    Map<Dish, Integer> deleteFromDishMap(HttpSession session, long dishId);

    /**
     * Inserts the {@link Dish} object into a data storage
     *
     * @param dish the Dish entity
     * @see Dish
     */
    void insert(Dish dish);

    /**
     * Updates the {@link Dish} object in a data storage
     *
     * @param dish the Dish entity
     * @see {@link Dish}
     */
    void update(Dish dish);

    /**
     * Updates the isDeleted field in {@link Dish} object
     * in a data storage, realizes soft delete feature
     *
     * @param dish the Dish entity
     * @see {@link Dish}
     */
    void updateIsDeleted(Dish dish);

    /**
     * Returns a number of deleted dishes
     *
     * @param deleted is dish deleted
     * @return a number of deleted dishes
     * @see {@link Dish}
     */
    int dishesCount(boolean deleted);
}
