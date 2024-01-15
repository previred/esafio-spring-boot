package com.challenge.spa.adapter.in.web.auth.resource;

import com.challenge.spa.domain.User;

public record AuthRequest(
        String username,
        String password
) {
  public User toUserDomain() {
    var domain = new User();
    domain.setUsername(username);
    domain.setPassword(password);
    return domain;
  }
}
