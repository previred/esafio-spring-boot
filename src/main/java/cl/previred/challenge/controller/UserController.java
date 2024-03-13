package cl.previred.challenge.controller;

import cl.previred.challenge.api.UsersApi;
import cl.previred.challenge.dto.UserDto;
import cl.previred.challenge.mapper.UserMapper;
import cl.previred.challenge.model.User;
import cl.previred.challenge.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController implements UsersApi {

  private final UserService userService;
  private final UserMapper userMapper;

  @Autowired
  public UserController(UserService userService, UserMapper userMapper) {
    this.userService = userService;
    this.userMapper = userMapper;
  }

  @Override
  public ResponseEntity<List<UserDto>> usersGet() {
    List<UserDto> users = userService.findAll().stream()
      .map(userMapper::toDto)
      .collect(Collectors.toList());
    return ResponseEntity.ok(users);
  }

  @Override
  public ResponseEntity<UserDto> usersPost(@Valid @RequestBody UserDto userDto) {
    User user = userService.createUser(userMapper.toEntity(userDto));
    return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<UserDto> usersUserIdGet(@PathVariable Long userId) {
    User user = userService.findById(userId);
    return ResponseEntity.ok(userMapper.toDto(user));
  }

  @Override
  public ResponseEntity<UserDto> usersUserIdPut(@PathVariable Long userId, @Valid @RequestBody UserDto userDto) {
    User user = userService.updateUser(userId, userMapper.toEntity(userDto));
    return ResponseEntity.ok(userMapper.toDto(user));
  }

  @Override
  public ResponseEntity<Void> usersUserIdDelete(@PathVariable Long userId) {
    userService.deleteUser(userId);
    return ResponseEntity.noContent().build();
  }

}
