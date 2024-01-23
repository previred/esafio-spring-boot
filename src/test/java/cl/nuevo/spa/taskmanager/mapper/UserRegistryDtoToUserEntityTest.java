package cl.nuevo.spa.taskmanager.mapper;

import static org.mockito.ArgumentMatchers.anyString;

import cl.nuevo.spa.taskmanager.domain.common.UserRole;
import cl.nuevo.spa.taskmanager.domain.dto.UserRegistryDto;
import cl.nuevo.spa.taskmanager.domain.entity.UserEntity;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UserRegistryDtoToUserEntityTest {

  private static final String JWT_DUMMY_TOKEN = "JWT_DUMMY_TOKEN";
  private static final String NAME = "NAME";
  private static final String USER_NAME = "USER_NAME";
  private static final String PASSWORD = "USER_NAME";
  private static final String PASSWORD_ENCRYPT = "PASSWORD_ENCRYPT";
  @InjectMocks UserRegistryDtoToUserEntity userRegistryDtoToUserEntity;
  @Mock private PasswordEncoder passwordEncoder;

  @Test
  void shouldMapUserRegistryDtoToUserEntity() {
    UserRegistryDto userRegistryDto = buildUserRegistryDto();
    UserEntity userEntity = buildUserEntity();
    Mockito.when(passwordEncoder.encode(anyString())).thenReturn(PASSWORD_ENCRYPT);
    Assertions.assertEquals(userEntity, userRegistryDtoToUserEntity.map(userRegistryDto));
  }

  private UserRegistryDto buildUserRegistryDto() {
    return UserRegistryDto.builder()
        .userName(USER_NAME)
        .name(NAME)
        .roles(Set.of(UserRole.ROLE_USER))
        .password(PASSWORD)
        .build();
  }

  private UserEntity buildUserEntity() {
    return UserEntity.builder()
        .userName(USER_NAME)
        .name(NAME)
        .password(PASSWORD_ENCRYPT)
        .roles(Set.of(UserRole.ROLE_USER))
        .build();
  }
}
