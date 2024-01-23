package cl.nuevo.spa.taskmanager.service;

import cl.nuevo.spa.taskmanager.domain.dto.UserDto;
import cl.nuevo.spa.taskmanager.domain.dto.UserRegistryDto;
import cl.nuevo.spa.taskmanager.domain.entity.UserEntity;
import cl.nuevo.spa.taskmanager.domain.exception.UserNotFoundException;
import cl.nuevo.spa.taskmanager.mapper.UserDtoToUserEntityMapper;
import cl.nuevo.spa.taskmanager.mapper.UserEntityToUserDto;
import cl.nuevo.spa.taskmanager.mapper.UserRegistryDtoToUserEntity;
import cl.nuevo.spa.taskmanager.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** The type User service. */
@Service
@RequiredArgsConstructor
public class UserService {

  private static final String ERROR_CODE_GET_USER_BY_ID = "0240401";
  private static final String ERROR_CODE_GET_USER_BY_USER_NAME = "0240402";
  private static final String ERROR_UPDATE_USER_BY_ID = "0240403";
  private static final String ERROR_CODE_DELETE_USER_BY_ID = "0240404";

  private final UserRepository userRepository;
  private final UserDtoToUserEntityMapper userDtoToUserEntityMapper;
  private final UserRegistryDtoToUserEntity userRegistryDtoToUserEntity;
  private final UserEntityToUserDto userEntityToUserDto;

  /**
   * Gets all users.
   *
   * @return the all users
   */
  public List<UserDto> getAllUsers() {
    List<UserEntity> users = userRepository.findAll();
    return users.stream().map(userEntityToUserDto::map).toList();
  }

  /**
   * Gets user by id.
   *
   * @param userId the user id
   * @return the user by id
   */
  public UserDto getUserById(Long userId) {
    UserEntity user =
        userRepository
            .findById(userId)
            .orElseThrow(
                () -> new UserNotFoundException(String.valueOf(userId), ERROR_CODE_GET_USER_BY_ID));
    return userEntityToUserDto.map(user);
  }

  /**
   * Gets user by user name.
   *
   * @param userName the user name
   * @return the user by user name
   */
  public UserDto getUserByUserName(String userName) {
    UserEntity userEntity =
        userRepository
            .findByUserName(userName)
            .orElseThrow((() -> new UserNotFoundException(null, ERROR_CODE_GET_USER_BY_USER_NAME)));
    return userEntityToUserDto.map(userEntity);
  }

  /**
   * Create user user dto.
   *
   * @param userRegistryDto the user registry dto
   * @return the user dto
   */
  public UserDto createUser(UserRegistryDto userRegistryDto) {
    UserEntity userEntity = userRegistryDtoToUserEntity.map(userRegistryDto);
    UserEntity createdUser = userRepository.save(userEntity);
    UserDto userDto = userEntityToUserDto.map(createdUser);
    return userDto;
  }

  /**
   * Update user user dto.
   *
   * @param userDto the user dto
   * @return the user dto
   */
  public UserDto updateUser(UserDto userDto) {
    UserEntity existingUserEntity =
        userRepository
            .findById(userDto.getId())
            .orElseThrow(
                () ->
                    new UserNotFoundException(
                        String.valueOf(userDto.getId()), ERROR_UPDATE_USER_BY_ID));
    UserEntity updatedUserEntity = userDtoToUserEntityMapper.map(userDto, existingUserEntity);
    UserEntity savedUserEntity = userRepository.save(updatedUserEntity);
    return userEntityToUserDto.map(savedUserEntity);
  }

  /**
   * Delete user.
   *
   * @param userId the user id
   */
  public void deleteUser(Long userId) {
    if (!userRepository.existsById(userId)) {
      throw new UserNotFoundException(String.valueOf(userId), ERROR_CODE_DELETE_USER_BY_ID);
    }
    userRepository.deleteById(userId);
  }
}
