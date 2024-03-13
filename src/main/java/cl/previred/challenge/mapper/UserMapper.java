package cl.previred.challenge.mapper;

import cl.previred.challenge.dto.UserDto;
import cl.previred.challenge.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  User toEntity(UserDto userDto);

  UserDto toDto(User user);

  @Mapping(target = "id", ignore = true)
  UserDto createUserDTOWithoutId(User user);
}
