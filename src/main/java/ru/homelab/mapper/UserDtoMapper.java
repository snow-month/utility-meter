package ru.homelab.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.homelab.dto.UserDto;
import ru.homelab.entity.User;

@Mapper
public interface UserDtoMapper {
    UserDtoMapper INSTANCE = Mappers.getMapper(UserDtoMapper.class);

    User userDtoToUser(UserDto userDto);
}
