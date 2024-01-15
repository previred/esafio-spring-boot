package com.challenge.spa.application.port.out.jwt;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtPort {
  String getUsername(String token);
  boolean isTokenValid(String token, UserDetails userDetails);
  String getToken(UserDetails user);
}
