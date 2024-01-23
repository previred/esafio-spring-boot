package cl.nuevo.spa.taskmanager.mapper;

import static org.mockito.ArgumentMatchers.any;

import cl.nuevo.spa.taskmanager.domain.common.UserRole;
import cl.nuevo.spa.taskmanager.domain.dto.UserDto;
import cl.nuevo.spa.taskmanager.domain.entity.UserEntity;
import cl.nuevo.spa.taskmanager.service.JwtService;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;

@ExtendWith(MockitoExtension.class)
class UserEntityToUserDtoTest {

  private static final String JWT_DUMMY_TOKEN = "JWT_DUMMY_TOKEN";
  private static final String NAME = "NAME";
  private static final Long USER_ID = 1L;
  private static final String USER_NAME = "USER_NAME";
  @InjectMocks UserEntityToUserDto userEntityToUserDto;
  @Mock private JwtService jwtService;

  @Test
  void shouldMapperUserEntityToUserDto() {
    Mockito.when(jwtService.generateToken(any(UserDetails.class), any(Map.class)))
        .thenReturn(JWT_DUMMY_TOKEN);
    UserEntity userEntity = buildTaskDto();
    UserDto expected = new UserDto();
    BeanUtils.copyProperties(userEntity, expected);
    expected.setJwt(JWT_DUMMY_TOKEN);
    Assertions.assertEquals(expected, userEntityToUserDto.map(userEntity));
  }

  private UserEntity buildTaskDto() {
    return UserEntity.builder()
        .id(USER_ID)
        .name(NAME)
        .userName(USER_NAME)
        .roles(Set.of(UserRole.ROLE_ADMIN))
        .build();
  }
}
