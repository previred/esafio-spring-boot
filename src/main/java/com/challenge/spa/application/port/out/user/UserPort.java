package com.challenge.spa.application.port.out.user;

import com.challenge.spa.domain.User;

import java.util.Optional;

public interface UserPort {
  Optional<User> findByUsername(String name);
  void save(User user);
}
