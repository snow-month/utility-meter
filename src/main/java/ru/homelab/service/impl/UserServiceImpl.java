package ru.homelab.service.impl;

import ru.homelab.entity.User;
import ru.homelab.exception.NoUserException;
import ru.homelab.repository.UserRepository;
import ru.homelab.service.UserService;

/**
 * The type User service.
 */
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    /**
     * Instantiates a new User service.
     *
     * @param userRepository the user repository
     */
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByLogin(String login) throws NoUserException {
        return userRepository.getUserByLogin(login);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
