package com.challenge.spa.adapter.in.web.auth.resource;

import com.challenge.spa.domain.Auth;

public record AuthResponse(
        String token
) {

  public static AuthResponse fromDomain(Auth auth) {
    return new AuthResponse(auth.getToken());
  }
}
