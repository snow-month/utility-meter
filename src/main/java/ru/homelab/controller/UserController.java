package ru.homelab.controller;

import ru.homelab.entity.Role;
import ru.homelab.entity.User;
import ru.homelab.exception.NoUserException;
import ru.homelab.service.UserService;

/**
 * The type User controller.
 */
public class UserController {
    private final UserService userService;

    /**
     * Instantiates a new User controller.
     *
     * @param userService the user service
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Gets user by login.
     *
     * @param login the login
     * @return the user by login
     * @throws NoUserException the no user exception
     */
    public User getUserByLogin(String login) throws NoUserException {
        return userService.getUserByLogin(login);
    }

    /**
     * Create new user.
     *
     * @param login    the login
     * @param password the password
     */
    public void createNewUser(String login, String password) {
        // todo String encrypt(String password) в утилиты
        User user = new User(login, password, Role.USER.name());
        userService.save(user);
    }
}
