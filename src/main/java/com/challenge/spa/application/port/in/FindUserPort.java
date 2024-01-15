package com.challenge.spa.application.port.in;

import com.challenge.spa.domain.User;

public interface FindUserPort {
  User findByUsername(String username);
}
