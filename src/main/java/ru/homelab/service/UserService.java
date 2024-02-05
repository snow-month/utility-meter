package ru.homelab.service;

import ru.homelab.entity.User;
import ru.homelab.exception.NoUserException;

/**
 * The interface User service.
 */
public interface UserService {
    /**
     * Gets user by login.
     *
     * @param login the login
     * @return the user by login
     * @throws NoUserException the no user exception
     */
    User getUserByLogin(String login) throws NoUserException;

    /**
     * Save.
     *
     * @param user the user
     */
    void save(User user);
}
