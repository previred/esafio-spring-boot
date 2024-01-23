package cl.nuevo.spa.taskmanager.mapper;

import cl.nuevo.spa.taskmanager.domain.dto.UserRegistryDto;
import cl.nuevo.spa.taskmanager.domain.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/** The type User registry dto to user entity. */
@Component
@AllArgsConstructor
public class UserRegistryDtoToUserEntity implements Mapper<UserRegistryDto, UserEntity> {

  private final PasswordEncoder passwordEncoder;

  @Override
  public UserEntity map(UserRegistryDto userRegistryDto) {
    return UserEntity.builder()
        .name(userRegistryDto.getName())
        .userName(userRegistryDto.getUserName())
        .password(passwordEncoder.encode(userRegistryDto.getPassword()))
        .roles(userRegistryDto.getRoles())
        .build();
  }
}
