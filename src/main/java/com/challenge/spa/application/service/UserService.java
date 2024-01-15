package com.challenge.spa.application.service;

import com.challenge.spa.application.port.in.FindUserPort;
import com.challenge.spa.application.port.out.user.UserPort;
import com.challenge.spa.common.UseCase;
import com.challenge.spa.domain.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@UseCase
public class UserService implements FindUserPort {

  private final UserPort userPort;

  public UserService(UserPort userPort) {
    this.userPort = userPort;
  }

  @Override
  public User findByUsername(String username) {
    return userPort
            .findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }
}
