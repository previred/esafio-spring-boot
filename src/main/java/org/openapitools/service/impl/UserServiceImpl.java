package org.openapitools.service.impl;

import org.openapitools.dto.User;
import org.openapitools.dto.UserPaginated;
import org.openapitools.repository.UserRepository;
import org.openapitools.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public UserPaginated getAllPaginated(Integer size, Integer page) {
        return userRepository.getAllPaginated(size, page);
    }
}
