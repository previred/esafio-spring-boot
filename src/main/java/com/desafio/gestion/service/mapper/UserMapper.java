package com.desafio.gestion.service.mapper;

import com.desafio.gestion.domain.User;
import com.desafio.gestion.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDto(User user);
    List<UserDTO> usersToUserDtos(List<User> users);
}
