package cl.corellana.taskmanager.service.impl;

import cl.corellana.taskmanager.api.model.LoginRequest;
import cl.corellana.taskmanager.api.model.LoginResponse;
import cl.corellana.taskmanager.api.model.SignUpRequest;
import cl.corellana.taskmanager.persistence.entities.UserEntity;
import cl.corellana.taskmanager.persistence.entities.UserMapper;
import cl.corellana.taskmanager.persistence.repositories.UserRepository;
import cl.corellana.taskmanager.service.JwtService;
import cl.corellana.taskmanager.service.LoginService;
import cl.corellana.taskmanager.service.SignUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements LoginService, SignUpService {

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginResponse authenticate(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserEntity user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();

        return LoginResponse.builder().token(jwtService.generateToken(user)).build();
    }

    @Override
    public void signUp(SignUpRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(UserMapper.INSTANCE.dtoToEntity(request));
    }
}
