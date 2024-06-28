package com.previred.taskmanager.service;

import com.previred.taskmanager.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createNewUser(UserDto userDto);
    List<UserDto> getAllUsers();
    UserDto getById(Long id);
    UserDto getByEmail(String email);
    UserDto update(UserDto userDto);
    UserDto updatePassword(Long id, String password);
    void delete(Long id);

}
