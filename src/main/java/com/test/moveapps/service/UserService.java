package com.test.moveapps.service;

import com.test.moveapps.domain.dto.UserDto;
import com.test.moveapps.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> store(UserDto userDto);

    Optional<User> search(String email);

    Optional<String> authenticate(String email, String password);

    List<User> search();

    Optional<User> findById(Long id);
}
