package com.labproject.restaurant.dao;

import com.labproject.restaurant.entities.Dish;

import java.util.List;

/**
 * Data Access Object interface for the {@link Dish} objects.
 * Provides CRUD operations with {@link Dish} objects.
 *
 * @author Igor Pevtsov
 * @author Alexandr Zorin
 * @author Vsevolod Beshanov
 * @author Zhanna Fedorova
 */
public interface DishDao {

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
     * Returns a List of {@link Dish} objects from a data storage
     *
     * @return the list of Dish objects
     * @see {@link Dish}
     */
    List<Dish> getAll();

    /**
     * Returns a List of {@link Dish} objects from a data storage
     * with isDeletet parameter set false
     *
     * @return the list of Dish objects
     * @see {@link Dish}
     */
    List<Dish> getAvailable();

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
     * @param page page number
     * @param countPerPage number of dishes on page
     * @param deleted is dish deleted
     * @return the list of Dish objects
     * @see {@link Dish}
     */
    List<Dish> getPage(int page, int countPerPage, boolean deleted);

    /**
     * Returns a number of deleted dishes
     *
     * @param deleted is dish deleted
     * @return a number of deleted dishes
     * @see {@link Dish}
     */
    int dishesCount(boolean deleted);
}
