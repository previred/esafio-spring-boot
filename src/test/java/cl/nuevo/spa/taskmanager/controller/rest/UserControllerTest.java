package cl.nuevo.spa.taskmanager.controller.rest;

import cl.nuevo.spa.taskmanager.domain.dto.UserDto;
import cl.nuevo.spa.taskmanager.domain.dto.UserRegistryDto;
import cl.nuevo.spa.taskmanager.service.UserService;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

  private static final Long USER_ID = 1L;
  @Mock private UserService userService;
  @InjectMocks private UserController userController;

  @Test
  void shouldReturnAllUserWhenSearchAllUser() {
    UserDto firstUserDto = UserDto.builder().build();
    UserDto secondUserDto = UserDto.builder().build();
    List<UserDto> usersDto = List.of(firstUserDto, secondUserDto);

    Mockito.when(userService.getAllUsers()).thenReturn(usersDto);
    ResponseEntity<List<UserDto>> usersDtoResponseEntity = userController.getAllUsers();
    Assertions.assertEquals(
        HttpStatus.OK, usersDtoResponseEntity.getStatusCode(), "should return 200 when exist user");
    Assertions.assertEquals(
        usersDto.size(),
        Objects.requireNonNull(usersDtoResponseEntity.getBody()).size(),
        "should return all user expected");
    Assertions.assertEquals(
        usersDto, usersDtoResponseEntity.getBody(), "Should be the same users as expected");
  }

  @Test
  void shouldReturnUserWhenSearchUserById() {
    UserDto userDTO = UserDto.builder().build();
    Mockito.when(userService.getUserById(Mockito.anyLong())).thenReturn(userDTO);
    ResponseEntity<UserDto> userDtoResponseEntity = userController.getUserById(USER_ID);
    Assertions.assertEquals(
        HttpStatus.OK,
        userDtoResponseEntity.getStatusCode(),
        "should return 200 when search all user its ok");
    Assertions.assertEquals(
        userDTO, userDtoResponseEntity.getBody(), "should be equals to expected user");
  }

  @Test
  void shouldCreateUserWhenRequestItsOk() {
    UserRegistryDto userRegistry = UserRegistryDto.builder().build();
    UserDto userDto = UserDto.builder().build();
    Mockito.when(userService.createUser(Mockito.any(UserRegistryDto.class))).thenReturn(userDto);
    ResponseEntity<UserDto> userDtoResponseEntity = userController.createUser(userRegistry);
    Assertions.assertEquals(
        HttpStatus.CREATED,
        userDtoResponseEntity.getStatusCode(),
        "should return 201 when user its created");
    Assertions.assertEquals(
        userDto, userDtoResponseEntity.getBody(), "should be equals to expected user");
  }

  @Test
  void shouldUpdateUserWhenRequestItsOk() {
    UserDto userDTO = UserDto.builder().build();
    Mockito.when(userService.updateUser(Mockito.any(UserDto.class))).thenReturn(userDTO);
    ResponseEntity<UserDto> userDtoResponseEntity = userController.updateUser(userDTO);
    Assertions.assertEquals(
        HttpStatus.OK,
        userDtoResponseEntity.getStatusCode(),
        "should return 200 when user its created");
    Assertions.assertEquals(
        userDTO, userDtoResponseEntity.getBody(), "should be equals to expected user");
  }

  @Test
  void shouldDeleteUserWhenUserExist() {
    ResponseEntity<?> userDtoResponseEntity = userController.deleteUser(USER_ID);
    Assertions.assertEquals(
        HttpStatus.NO_CONTENT,
        userDtoResponseEntity.getStatusCode(),
        "should return 204 when user its deleted");
  }
}
