package com.labproject.restaurant.services;

import com.labproject.restaurant.entities.User;

/**
 * A service layer interface that provides
 * functionality with {@link User} account
 *
 * @author Igor Pevtsov
 * @author Alexandr Zorin
 * @author Vsevolod Beshanov
 * @author Zhanna Fedorova
 * @see User
 */
public interface AccountService {

    /**
     * Inserts the {@link User} object into a data storage
     *
     * @param user the User entity
     * @see User
     */
    void doRegister(User user);

    /**
     * Returns {@link User} object if login and password are correct
     *
     * @param user
     * @return the User entity
     * @throws IllegalArgumentException
     */
    User validateUser(User user);
}
