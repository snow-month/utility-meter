package ru.homelab.service.impl;

import ru.homelab.dto.UserDto;
import ru.homelab.entity.User;
import ru.homelab.exception.NoUserException;
import ru.homelab.exception.ValidationException;
import ru.homelab.mapper.UserDtoMapper;
import ru.homelab.repository.UserRepository;
import ru.homelab.service.UserService;
import ru.homelab.validator.CreateUserValidator;
import ru.homelab.validator.ValidationResult;

import java.sql.SQLException;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final CreateUserValidator validator = new CreateUserValidator();
    private UserRepository userRepository;

    public UserServiceImpl() {
    }

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByLogin(String login) throws NoUserException {
        return userRepository.getUserByLogin(login);
    }

    @Override
    public Long create(UserDto userDto) throws ValidationException, SQLException {
        ValidationResult validationResult = validator.isValid(userDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }

        User user = userRepository.save(UserDtoMapper.INSTANCE.userDtoToUser(userDto));
        return user.getId();
    }

    @Override
    public Optional<User> login(String login, String password) throws SQLException {
        Optional<User> currentUser = userRepository.findByLoginAndPassword(login, password);
        currentUser.ifPresent(user -> user.setPassword(null));
        return currentUser;
    }
}
