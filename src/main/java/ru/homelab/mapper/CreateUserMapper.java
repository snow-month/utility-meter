package ru.homelab.mapper;

import ru.homelab.dto.CreateUserDto;
import ru.homelab.entity.Role;
import ru.homelab.entity.User;

// todo MapStruct
public class CreateUserMapper implements Mapper<CreateUserDto, User> {
    @Override
    public User mapFrom(CreateUserDto object) {
        return new User(
                object.getLogin(),
                object.getPassword(),
                Role.valueOf(object.getRole())
        );
    }
}
