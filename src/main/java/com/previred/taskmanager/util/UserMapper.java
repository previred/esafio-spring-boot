package com.previred.taskmanager.util;

import com.previred.taskmanager.dto.UserDto;
import com.previred.taskmanager.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "role.name", target = "role")
    UserDto userToUserDto(User user);

    @Mapping(source = "role", target = "role.name")
    User userDtoToUser(UserDto userDto);
}
