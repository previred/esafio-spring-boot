package ar.com.challenge.desafio_spring_boot.services.impl;

import ar.com.challenge.desafio_spring_boot.dto.SigninDto;
import ar.com.challenge.desafio_spring_boot.dto.SignupDto;
import ar.com.challenge.desafio_spring_boot.entity.Rol;
import ar.com.challenge.desafio_spring_boot.entity.User;
import ar.com.challenge.desafio_spring_boot.exception.ResourceFoundException;
import ar.com.challenge.desafio_spring_boot.exception.ResourceNotFoundException;
import ar.com.challenge.desafio_spring_boot.mapper.UserMapper;
import ar.com.challenge.desafio_spring_boot.repository.RolRepository;
import ar.com.challenge.desafio_spring_boot.repository.UserRepository;
import ar.com.challenge.desafio_spring_boot.services.JwtService;
import ar.com.challenge.desafio_spring_boot.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RolRepository rolRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    @Override
    public String loadUser(SigninDto userDto) throws ResourceNotFoundException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));

        Optional<User> user = userRepository.findByUsername(userDto.getUsername());

        if (user.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }

        return jwtService.getToken(user.get());
    }

    @Override
    public String createUser(SignupDto userDto) throws ResourceNotFoundException, ResourceFoundException {

        Optional<User> user = userRepository.findByUsername(userDto.getUsername());

        if (user.isPresent()) {
            throw new ResourceFoundException("User exist");
        }

        Optional<Rol> rol = rolRepository.findByName(userDto.getRole());

        if (rol.isEmpty()) {
            throw new ResourceNotFoundException("Role not found");
        }

        User userNew = UserMapper.toEntity(userDto);

        userNew.setRole(rol.get());

        userRepository.save(userNew);

        return jwtService.getToken(userNew);
    }
}
