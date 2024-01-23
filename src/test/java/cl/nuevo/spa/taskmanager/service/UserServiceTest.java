package cl.nuevo.spa.taskmanager.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import cl.nuevo.spa.taskmanager.domain.dto.UserDto;
import cl.nuevo.spa.taskmanager.domain.dto.UserRegistryDto;
import cl.nuevo.spa.taskmanager.domain.entity.UserEntity;
import cl.nuevo.spa.taskmanager.domain.exception.UserNotFoundException;
import cl.nuevo.spa.taskmanager.mapper.UserDtoToUserEntityMapper;
import cl.nuevo.spa.taskmanager.mapper.UserEntityToUserDto;
import cl.nuevo.spa.taskmanager.mapper.UserRegistryDtoToUserEntity;
import cl.nuevo.spa.taskmanager.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @Mock private UserRepository userRepository;
  @Mock private UserDtoToUserEntityMapper userDtoToUserEntityMapper;
  @Mock private UserRegistryDtoToUserEntity userRegistryDtoToUserEntity;
  @Mock private UserEntityToUserDto userEntityToUserDto;

  @InjectMocks private UserService userService;

  @Test
  void shouldRetrieveAllUsers() {
    UserEntity firstUserEntity = UserEntity.builder().build();
    UserEntity secondUserEntity = UserEntity.builder().build();
    List<UserEntity> usersEntity = List.of(firstUserEntity, secondUserEntity);
    when(userRepository.findAll()).thenReturn(usersEntity);
    UserDto firstUserDto = UserDto.builder().build();
    UserDto secondUserDto = UserDto.builder().build();
    List<UserDto> expected = List.of(firstUserDto, secondUserDto);
    when(userEntityToUserDto.map(firstUserEntity)).thenReturn(firstUserDto);
    when(userEntityToUserDto.map(secondUserEntity)).thenReturn(secondUserDto);
    Assertions.assertEquals(expected, userService.getAllUsers());
    Mockito.verify(userRepository, Mockito.times(1)).findAll();
    Mockito.verify(userEntityToUserDto, Mockito.times(2)).map(any(UserEntity.class));
  }

  @Test
  void shouldRetrieveUserById() {
    UserEntity userEntity = UserEntity.builder().build();
    UserDto userDto = UserDto.builder().build();
    when(userRepository.findById(anyLong())).thenReturn(Optional.of(userEntity));
    when(userEntityToUserDto.map(userEntity)).thenReturn(userDto);
    Assertions.assertEquals(userDto, userService.getUserById(anyLong()));
    Mockito.verify(userRepository, Mockito.times(1)).findById(anyLong());
    Mockito.verify(userEntityToUserDto, Mockito.times(1)).map(any(UserEntity.class));
  }

  @Test
  void shouldThrowExceptionWhenUserIdNotFoundInDb() {
    when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
    Assertions.assertThrows(UserNotFoundException.class, () -> userService.getUserById(anyLong()));
    Mockito.verify(userRepository, Mockito.times(1)).findById(anyLong());
    Mockito.verify(userEntityToUserDto, Mockito.times(0)).map(any(UserEntity.class));
  }

  @Test
  void shouldRetrieveUserByUserName() {
    UserEntity userEntity = UserEntity.builder().build();
    UserDto userDto = UserDto.builder().build();
    when(userRepository.findByUserName(any())).thenReturn(Optional.of(userEntity));
    when(userEntityToUserDto.map(userEntity)).thenReturn(userDto);
    Assertions.assertEquals(userDto, userService.getUserByUserName(any()));
    Mockito.verify(userRepository, Mockito.times(1)).findByUserName(any());
    Mockito.verify(userEntityToUserDto, Mockito.times(1)).map(any(UserEntity.class));
  }

  @Test
  void shouldThrowExceptionWhenUserNameNotFoundInDb() {
    when(userRepository.findByUserName(any())).thenReturn(Optional.empty());
    Assertions.assertThrows(
        UserNotFoundException.class, () -> userService.getUserByUserName(any()));
    Mockito.verify(userRepository, Mockito.times(1)).findByUserName(any());
    Mockito.verify(userEntityToUserDto, Mockito.times(0)).map(any(UserEntity.class));
  }

  @Test
  void shouldReturnUserWhenCreateUserItsOk() {
    UserEntity userEntity = UserEntity.builder().build();
    UserDto expected = UserDto.builder().build();
    UserRegistryDto userRegistryDto = UserRegistryDto.builder().build();
    when(userRepository.save(userEntity)).thenReturn(userEntity);
    when(userRegistryDtoToUserEntity.map(userRegistryDto)).thenReturn(userEntity);
    when(userEntityToUserDto.map(userEntity)).thenReturn(expected);
    Assertions.assertEquals(expected, userService.createUser(userRegistryDto));
    Mockito.verify(userRepository, Mockito.times(1)).save(any(UserEntity.class));
    Mockito.verify(userEntityToUserDto, Mockito.times(1)).map(any(UserEntity.class));
    Mockito.verify(userRegistryDtoToUserEntity, Mockito.times(1)).map(any(UserRegistryDto.class));
  }

  @Test
  void shouldUpdateUserWhenUserExist() {
    UserEntity userEntity = UserEntity.builder().id(0L).build();
    UserDto userDto = UserDto.builder().id(0L).build();
    when(userRepository.findById(anyLong())).thenReturn(Optional.of(userEntity));
    when(userRepository.save(userEntity)).thenReturn(userEntity);
    when(userDtoToUserEntityMapper.map(userDto, userEntity)).thenReturn(userEntity);
    when(userEntityToUserDto.map(userEntity)).thenReturn(userDto);
    Assertions.assertEquals(userDto, userService.updateUser((userDto)));
    Mockito.verify(userRepository, Mockito.times(1)).findById(anyLong());
    Mockito.verify(userRepository, Mockito.times(1)).save(any(UserEntity.class));
    Mockito.verify(userEntityToUserDto, Mockito.times(1)).map(any(UserEntity.class));
    Mockito.verify(userDtoToUserEntityMapper, Mockito.times(1))
        .map(any(UserDto.class), any(UserEntity.class));
  }

  @Test
  void shouldThrowExceptionWhenTryUpdateUserAndNotExist() {
    when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
    Assertions.assertThrows(UserNotFoundException.class, () -> userService.getUserById(anyLong()));
    Mockito.verify(userRepository, Mockito.times(1)).findById(anyLong());
    Mockito.verify(userEntityToUserDto, Mockito.times(0)).map(any(UserEntity.class));
  }

  @Test
  void shouldDeleteUserWhenUserExist() {
    when(userRepository.existsById(anyLong())).thenReturn(true);
    doNothing().when(userRepository).deleteById(anyLong());
    userService.deleteUser(anyLong());
    Mockito.verify(userRepository, Mockito.times(1)).existsById(anyLong());
    Mockito.verify(userRepository, Mockito.times(1)).deleteById(anyLong());
  }

  @Test
  void shouldThrowExceptionWhenTryDeleteUserAndUserNotExist() {
    when(userRepository.existsById(anyLong())).thenReturn(false);
    Assertions.assertThrows(UserNotFoundException.class, () -> userService.deleteUser(anyLong()));
    Mockito.verify(userRepository, Mockito.times(1)).existsById(anyLong());
    Mockito.verify(userRepository, Mockito.times(0)).deleteById(anyLong());
  }
}
