package com.previred.taskmanager.service;

import com.previred.taskmanager.dto.UserDto;
import com.previred.taskmanager.exception.TaskManagerException;
import com.previred.taskmanager.model.User;
import com.previred.taskmanager.repository.UserRepository;
import com.previred.taskmanager.util.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto createNewUser(UserDto userDto) {
        return userMapper.userToUserDto(userRepository.save(userMapper.userDtoToUser(userDto)));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::userToUserDto).toList();
    }

    @Override
    public UserDto getById(Long id) {
        return userMapper.userToUserDto(findById(id));
    }

    @Override
    public UserDto getByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new TaskManagerException(String.format("User with email %s not found", email)));
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto update(UserDto userDto) {
        findById(userDto.getId());
        return userMapper.userToUserDto(userRepository.save(userMapper.userDtoToUser(userDto)));
    }

    @Override
    public UserDto updatePassword(Long id, String password) {
        User user = findById(id);
        user.setPassword(password);
        return userMapper.userToUserDto(userRepository.save(user));
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(findById(id));
    }

    private User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new TaskManagerException("User not found: " + id));
    }
}
