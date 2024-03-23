package com.reto.tecnico.services;

import org.openapitools.model.UserRequest;
import org.openapitools.model.UserResponse;

public interface IAuthService {
  UserResponse authenticate(UserRequest authRequest);
}
