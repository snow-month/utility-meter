package ru.homelab.repository;

import ru.homelab.entity.User;
import ru.homelab.exception.NoUserException;

import java.sql.SQLException;

/**
 * The interface User repository.
 */
public interface UserRepository {
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
     * @return
     */
    User save(User user) throws SQLException;
}
