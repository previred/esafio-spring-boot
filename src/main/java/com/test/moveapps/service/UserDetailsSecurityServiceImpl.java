package com.test.moveapps.service;


import com.test.moveapps.domain.entity.User;
import com.test.moveapps.exception.UserApiActionException;
import com.test.moveapps.repository.UserRepository;
import com.test.moveapps.security.provider.JwtProvider;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.springframework.security.core.userdetails.User.withUsername;

@Service
public class UserDetailsSecurityServiceImpl implements UserDetailsSecurityService {

    private final UserRepository userRepository;

    private final JwtProvider jwtProvider;

    @Autowired
    public UserDetailsSecurityServiceImpl(UserRepository userRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findUserByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("email.no.existe"));

        return withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRoles())
                .credentialsExpired(false)
                .accountLocked(false)
                .accountExpired(false)
                .disabled(false)
                .build();
    }


    @Override
    public Optional<UserDetails> loadUserByJwtToken(String token) throws ExpiredJwtException {

            if (jwtProvider.validateToken(token)) {
                return Optional.of(
                        withUsername(jwtProvider.getUsername(token))
                                .authorities(jwtProvider.getRoles(token))
                                .password("")
                                .accountExpired(false)
                                .accountLocked(false)
                                .credentialsExpired(false)
                                .disabled(false)
                                .build());
            }

        return Optional.empty();
    }
}
