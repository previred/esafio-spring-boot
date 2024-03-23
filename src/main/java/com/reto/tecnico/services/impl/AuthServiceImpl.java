package com.reto.tecnico.services.impl;

import com.reto.tecnico.exceptions.CustomException;
import com.reto.tecnico.model.User;
import com.reto.tecnico.repository.IUserRepository;
import com.reto.tecnico.security.services.JwtUtilService;
import com.reto.tecnico.services.IAuthService;
import org.openapitools.model.UserRequest;
import org.openapitools.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthServiceImpl implements IAuthService {

  private final IUserRepository userRepository;

  private final AuthenticationManager authenticationManager;

  private final UserDetailsService userDetailsService;

  private final JwtUtilService jwtUtilService;

  public AuthServiceImpl(IUserRepository userRepository, AuthenticationManager authenticationManager,
                         UserDetailsService userDetailsService, JwtUtilService jwtUtilService){
    this.userRepository = userRepository;
    this.authenticationManager = authenticationManager;
    this.userDetailsService = userDetailsService;
    this.jwtUtilService = jwtUtilService;
  }

  @Override
  public UserResponse authenticate (UserRequest authRequest) {

    Optional<User> optionalUsers = userRepository.findByUsername(authRequest.getUsername());

    if(!optionalUsers.isPresent()) {
      throw new CustomException("Credenciales inválidas.");
    }

    User user = optionalUsers.get();

    if(!user.getPassword().equals(authRequest.getPassword())) {
      throw new CustomException("Credenciales inválidas.");
    }

    if(!user.getIsActive()) {
      throw new CustomException("El usuario se encuentra inactivo, contacta con el administrador.");
    }

    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
    UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
    UserResponse authResponse = jwtUtilService.generateToken(userDetails);

    user.setLastLogin(LocalDateTime.now());

    userRepository.save(user);
    authResponse.setName(user.getFullName());
    authResponse.setUsername(user.getUsername());

    return authResponse;
  }
}
