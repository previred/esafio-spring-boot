package cl.nuevo.spa.taskmanager.mapper;

import cl.nuevo.spa.taskmanager.domain.common.UserRole;
import cl.nuevo.spa.taskmanager.domain.dto.UserDto;
import cl.nuevo.spa.taskmanager.domain.entity.UserEntity;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

class UserDtoToUserEntityMapperTest {

  private static final String NEW_NAME = "NEW_NAME";
  private static final String NAME = "NAME";

  private static final Long USER_ID = 1L;
  private static final String USER_NAME = "USER_NAME";
  private final UserDtoToUserEntityMapper userDtoToUserEntityMapper =
      new UserDtoToUserEntityMapper();

  @Test
  void shouldMapUserDtoToUserEntityOk() {
    UserDto userDto = buildUserDto();
    UserEntity expected = new UserEntity();
    BeanUtils.copyProperties(userDto, expected);
    Assertions.assertEquals(
        expected,
        userDtoToUserEntityMapper.map(userDto),
        "Should mapper all properties from UserDto");
  }

  @Test
  void shouldUpdateMapUserDtoToUserEntityOk() {
    UserDto userDto = buildUserDto();
    UserEntity userEntityInBd = new UserEntity();
    BeanUtils.copyProperties(userDto, userEntityInBd);
    userDto.setName(NEW_NAME);
    UserEntity expected = new UserEntity();
    BeanUtils.copyProperties(userDto, expected);
    Assertions.assertEquals(
        expected,
        userDtoToUserEntityMapper.map(userDto, userEntityInBd),
        "Should mapper all properties from UserEntity and UserDto");
  }

  private UserDto buildUserDto() {
    return UserDto.builder()
        .id(USER_ID)
        .name(NAME)
        .userName(USER_NAME)
        .roles(Set.of(UserRole.ROLE_ADMIN))
        .build();
  }
}
