package cl.nuevo.spa.taskmanager.mapper;

import cl.nuevo.spa.taskmanager.domain.dto.UserDto;
import cl.nuevo.spa.taskmanager.domain.entity.UserAuthDetailsEntity;
import cl.nuevo.spa.taskmanager.domain.entity.UserEntity;
import cl.nuevo.spa.taskmanager.service.JwtService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/** The type User entity to user dto. */
@Component
@RequiredArgsConstructor
public class UserEntityToUserDto implements Mapper<UserEntity, UserDto> {

  private static final String NAME = "name";
  private static final String ROLE = "role";
  private static final String AUTHORITIES = "authorities";

  private final JwtService jwtService;

  @Override
  public UserDto map(UserEntity userEntity) {
    return UserDto.builder()
        .id(userEntity.getId())
        .name(userEntity.getName())
        .userName(userEntity.getUserName())
        .roles(userEntity.getRoles())
        .jwt(buildJwtToken(userEntity))
        .build();
  }

  private String buildJwtToken(UserEntity userEntity) {
    UserAuthDetailsEntity userAuthDetailsEntity = new UserAuthDetailsEntity(userEntity);
    return jwtService.generateToken(userAuthDetailsEntity, buildExtraClaims(userAuthDetailsEntity));
  }

  private Map<String, Object> buildExtraClaims(UserAuthDetailsEntity userAuthDetailsEntity) {
    return Map.of(
        NAME,
        userAuthDetailsEntity.getUser().getName(),
        ROLE,
        userAuthDetailsEntity.getUser().getRoles(),
        AUTHORITIES,
        userAuthDetailsEntity.getAuthorities());
  }
}
