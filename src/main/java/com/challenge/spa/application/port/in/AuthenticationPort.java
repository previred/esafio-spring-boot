package com.challenge.spa.application.port.in;

import com.challenge.spa.domain.Auth;
import com.challenge.spa.domain.User;

public interface AuthenticationPort {
  Auth authenticate(User user);
}
