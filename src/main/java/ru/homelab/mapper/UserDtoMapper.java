package ru.homelab.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.homelab.dto.UserDto;
import ru.homelab.entity.User;

@Mapper
public interface UserDtoMapper {
    UserDtoMapper INSTANCE = Mappers.getMapper(UserDtoMapper.class);

    @Mapping(target = "id", ignore = true)
    User userDtoToUser(UserDto userDto);
}
