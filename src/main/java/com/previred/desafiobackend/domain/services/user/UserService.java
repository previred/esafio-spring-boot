package com.previred.desafiobackend.domain.services.user;

import com.previred.desafiobackend.data.entities.User;
import com.previred.desafiobackend.data.repository.user.UserRepository;
import com.previred.desafiobackend.domain.exception.user.UserNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Miguel Angel
 * @since v1.0.0
 */

@Service
@Log4j2
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByEmail(String email) {
        log.info("[findByEmail] Searching user with email [{}]", email);
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::thrown);
    }
}
