package com.desafio.spring.ec.bs.services.config;

import com.desafio.spring.ec.bs.repository.UserRepository;
import com.desafio.spring.ec.bs.services.config.jwt.JwtTokenUtil;
import com.desafio.spring.ec.ds.entity.Users;
import com.desafio.spring.ec.ds.request.UserLogin;
import com.desafio.spring.ec.ds.request.UserRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtTokenUtil jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public String register(UserRegister userRegister) {


        Users userEntity = Users.builder()
                .name(userRegister.name())
                .email(userRegister.email())
                .lastname(userRegister.lastname())
                .password(passwordEncoder.encode(userRegister.password()))
                .roles(userRegister.roles())
                .username(userRegister.username())
                .build();

        userRepository.save(userEntity);

        return jwtService.generateToken(userEntity);
    }

    public String login(UserLogin userLogin) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.username(), userLogin.password()));

        Users userEntity = userRepository.findByUsername(userLogin.username())
                .orElseThrow(() -> new UsernameNotFoundException("Username " + userLogin.username() + " not found"));

        return jwtService.generateToken(userEntity);


    }
}
