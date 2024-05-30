package cl.previred.desafio.service;

import cl.previred.desafio.dto.LoginRequestDTO;
import cl.previred.desafio.dto.UserRequestDTO;
import cl.previred.desafio.entity.RoleEntity;
import cl.previred.desafio.entity.UserEntity;
import cl.previred.desafio.enums.RoleEnum;
import cl.previred.desafio.repository.RoleRepository;
import cl.previred.desafio.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserEntity signup(UserRequestDTO input) {
        Optional<RoleEntity> role = roleRepository.findByName(RoleEnum.valueOf(input.getRole()));
        if(!role.isPresent())
            throw new IllegalArgumentException("Role doesn't exists.");

        try{
            return userService.createUser(UserEntity.builder()
                    .fullname(input.getFullname())
                    .email(input.getEmail())
                    .password(passwordEncoder.encode(input.getPassword()))
                    .role(role.get())
                    .build());
        }catch (Exception e){
            throw new IllegalArgumentException("User already exists!");
        }
    }

    public UserEntity authenticate(LoginRequestDTO input) {
        Authentication a = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword())
        );
        return userRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new EntityNotFoundException(String.format("Admin %s not found", input.getEmail())));
    }

    public List<UserEntity> allUsers() {
        List<UserEntity> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }
}
