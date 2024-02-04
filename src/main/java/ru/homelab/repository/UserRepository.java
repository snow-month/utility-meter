package ru.homelab.repository;

import ru.homelab.entity.User;
import ru.homelab.exception.NoUserException;

public interface UserRepository {
    User getUserByLogin(String login) throws NoUserException;

    void save(User user);
}
