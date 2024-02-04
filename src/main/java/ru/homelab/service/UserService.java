package ru.homelab.service;

import ru.homelab.entity.User;
import ru.homelab.exception.NoUserException;

public interface UserService {
    User getUserByLogin(String login) throws NoUserException;

    void save(User user);
}
