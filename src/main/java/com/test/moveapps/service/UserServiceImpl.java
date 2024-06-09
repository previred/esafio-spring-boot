package com.test.moveapps.service;


import com.test.moveapps.domain.dto.UserDto;
import com.test.moveapps.domain.entity.Role;
import com.test.moveapps.domain.entity.User;
import com.test.moveapps.exception.UserApiActionException;
import com.test.moveapps.repository.RoleRepository;
import com.test.moveapps.repository.UserRepository;
import com.test.moveapps.security.provider.JwtProvider;
import com.test.moveapps.util.RoleType;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager,
                           JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    @Transactional
    @Override
    public Optional<User> store(UserDto userDto) {
        Optional<User> userSaved = Optional.empty();
        Optional<User> userExists = this.userRepository.findUserByUsername(userDto.getUsername());

        userExists.ifPresent(action -> {
            throw new UserApiActionException("El usuario ya existe", HttpStatus.CONFLICT);
        });

        Optional<Role> role = this.roleRepository.findByName(RoleType.ROLE_USER.name());
        if (role.isPresent()) {
            userDto.setToken(this.jwtProvider.generateToken(userDto.getUsername(), Collections.singletonList(role.get())));
            userSaved = Optional.of(
                    this.userRepository.save(
                            UserDto.convert(userDto, this.passwordEncoder.encode(userDto.getPassword()), role.get())
                    )
            );
        }
        return userSaved;
    }

    @Override
    public Optional<User> search(String username) {
        User user = this.userRepository.findUserByUsername(username).orElseThrow(() ->
                new UserApiActionException("Usuario no existe", HttpStatus.NOT_FOUND));
        return Optional.of(user);
    }

    @Transactional
    @Override
    public Optional<String> authenticate(String username, String password) {
        Optional<String> token = Optional.empty();
        Optional<User> userOptional = Optional.ofNullable(this.userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UserApiActionException("email.no.existe", HttpStatus.NOT_FOUND)));

        if(userOptional.isPresent()) {
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
                token = Optional.of(this.jwtProvider.generateToken(username, new ArrayList<>(userOptional.get().getRoles())));
                userOptional.get().setLastLogin(new Date());
                this.userRepository.save(userOptional.get());
            } catch (AuthenticationException e){
                throw new UserApiActionException("Credenciales invalidas", HttpStatus.UNAUTHORIZED);
            }
        }
        return token;
    }

    @Override
    public List<User> search() {
        List<User> users = new ArrayList<>();
        this.userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}
