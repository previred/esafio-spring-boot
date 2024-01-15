package com.challenge.spa.application.service;

import com.challenge.spa.application.port.in.AuthenticationPort;
import com.challenge.spa.application.port.in.FindUserPort;
import com.challenge.spa.application.port.out.jwt.JwtPort;
import com.challenge.spa.common.UseCase;
import com.challenge.spa.domain.Auth;
import com.challenge.spa.domain.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

@UseCase
public class AuthenticationService implements AuthenticationPort {

  private final FindUserPort findUserPort;
  private final JwtPort jwtService;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;

  public AuthenticationService(FindUserPort findUserPort, JwtPort jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
    this.findUserPort = findUserPort;
    this.jwtService = jwtService;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Auth authenticate(User user) {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
    );
    User userDetails = findUserPort.findByUsername(user.getUsername());
    String token = jwtService.getToken(userDetails);
    return new Auth(token);
  }
}
