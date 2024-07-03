package io.swagger.service;

import io.swagger.entity.UserEntity;
import io.swagger.exception.UserAlreadyExistsException;
import io.swagger.model.TokenResponse;
import io.swagger.model.User;
import io.swagger.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public User registerUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistsException(user.getUsername());
        }
        String password = passwordEncoder.encode(user.getPassword());
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(password);
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPhone(user.getPhone());
        userEntity.setUserStatus(1);

        Long userId = userRepository.save(userEntity).getId();

        user.setId(userId);

        return user;
    }

    public TokenResponse authenticate(String username, String password) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                username,
                                password
                        )
                );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtService.buildJWT(userDetails);

        return new TokenResponse().authToken(token);
    }

}
