package ru.homelab.controller;

import ru.homelab.entity.Role;
import ru.homelab.entity.User;
import ru.homelab.exception.NoUserException;
import ru.homelab.service.UserService;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public User getUserByLogin(String login) throws NoUserException {
        return userService.getUserByLogin(login);
    }

    public void createNewUser(String login, String password) {
        // todo String encrypt(String password) в утилиты
        User user = new User(login, password, Role.USER.name());
        userService.save(user);
    }
}
