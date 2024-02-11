package ru.homelab.validator;

import ru.homelab.dto.CreateUserDto;
import ru.homelab.entity.Role;

public class CreateUserValidator implements Validator<CreateUserDto> {
    @Override
    public ValidationResult isValid(CreateUserDto object) {
        var validationResult = new ValidationResult();
        if (object.getLogin().isEmpty()) {
            validationResult.add(new Error("invalid.login", "Логин пустой"));
        }
        if (object.getPassword().isEmpty()) {
            validationResult.add(new Error("invalid.password", "Пароль пустой"));
        }
        if (Role.find(object.getRole()).isEmpty()) {
            validationResult.add(new Error("invalid.role", "Роль не валидная"));
        }

        return validationResult;
    }
}
