package com.test.previred.domain.usecase.user;

import com.test.previred.domain.model.user.User;
import com.test.previred.domain.model.user.gateway.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUseCase {
    private final UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

}
