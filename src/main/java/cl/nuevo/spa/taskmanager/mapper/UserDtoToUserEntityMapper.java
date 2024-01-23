package cl.nuevo.spa.taskmanager.mapper;

import cl.nuevo.spa.taskmanager.domain.dto.UserDto;
import cl.nuevo.spa.taskmanager.domain.entity.UserEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/** The type User dto to user entity mapper. */
@Component
public class UserDtoToUserEntityMapper
    implements Mapper<UserDto, UserEntity>, BiMapper<UserDto, UserEntity, UserEntity> {

  @Override
  public UserEntity map(UserDto userDto, UserEntity userEntity) {
    UserEntity newUserEntity = new UserEntity();
    BeanUtils.copyProperties(userEntity, newUserEntity);
    BeanUtils.copyProperties(userDto, newUserEntity);
    newUserEntity.setId(userEntity.getId());
    return newUserEntity;
  }

  @Override
  public UserEntity map(UserDto userDto) {
    UserEntity userEntity = new UserEntity();
    BeanUtils.copyProperties(userDto, userEntity);
    return userEntity;
  }
}
