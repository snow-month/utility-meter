package ru.homelab.service.impl;

import ru.homelab.mapper.CreateUserMapper;
import ru.homelab.dto.CreateUserDto;
import ru.homelab.entity.User;
import ru.homelab.exception.NoUserException;
import ru.homelab.exception.ValidationException;
import ru.homelab.repository.UserRepository;
import ru.homelab.service.UserService;
import ru.homelab.validator.CreateUserValidator;
import ru.homelab.validator.ValidationResult;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    private final CreateUserValidator validator = new CreateUserValidator();
    private final CreateUserMapper mapper = new CreateUserMapper();
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByLogin(String login) throws NoUserException {
        return userRepository.getUserByLogin(login);
    }

    @Override
    public Long create(CreateUserDto createUserDto) throws ValidationException, SQLException {
        // validation
        // map
        // userRepository.save(user);
        // return id
        ValidationResult validationResult = validator.isValid(createUserDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        User userEntity = mapper.mapFrom(createUserDto);
        userRepository.save(userEntity);

        return userEntity.getId();
    }
}
