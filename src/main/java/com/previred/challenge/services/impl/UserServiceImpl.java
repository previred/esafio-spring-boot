package com.previred.challenge.services.impl;

import com.previred.challenge.dto.UserLoginRequestDTO;
import com.previred.challenge.exceptions.UserNotFoundException;
import com.previred.challenge.model.RegisteredUser;
import com.previred.challenge.repositories.UserRepository;
import com.previred.challenge.services.JWTService;
import com.previred.challenge.services.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @NonNull
    private final UserRepository userRepository;

    @NonNull
    private final PasswordEncoder passwordEncoder;

    @NonNull
    private final JWTService jwtService;

    public String authenticate(UserLoginRequestDTO userLoginRequestDTO) {
        String username = userLoginRequestDTO.email();
        RegisteredUser registeredUser = userRepository.findByEmail(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        if (!passwordEncoder.matches(userLoginRequestDTO.password(), registeredUser.getPassword())) {
            throw new UserNotFoundException(username);
        }

        return jwtService.generateToken(registeredUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var registeredUser = userRepository.findByEmail(username).orElseThrow();
        return new User(registeredUser.getEmail(), registeredUser.getPassword(), Collections.emptyList());
    }

}
