package ru.homelab.service;

import ru.homelab.dto.CreateUserDto;
import ru.homelab.entity.User;
import ru.homelab.exception.NoUserException;
import ru.homelab.exception.ValidationException;

import java.sql.SQLException;

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


    Long create(CreateUserDto createUserDto) throws ValidationException, SQLException;
}
