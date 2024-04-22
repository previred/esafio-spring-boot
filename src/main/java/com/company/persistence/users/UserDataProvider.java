package com.company.persistence.users;

import com.company.exception.AppException;
import com.company.exception.enums.CodeExceptions;
import com.company.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class UserDataProvider {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Optional<User>  findByUserId(UUID uuid) {
        return userRepository.findById(uuid)
                             .map(userMapper::toModel);
    }

    public Optional<User> findByUserAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password)
                             .map(userMapper::toModel);
    }

}
