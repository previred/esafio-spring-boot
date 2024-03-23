package com.reto.tecnico.controller;

import com.reto.tecnico.services.IAuthService;
import org.openapitools.api.AuthApi;
import org.openapitools.model.UserRequest;
import org.openapitools.model.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthApi {

  private final IAuthService authService;

  public AuthController (IAuthService authService) {
    this.authService = authService;
  }

  @Override
  public ResponseEntity<UserResponse> authLogin (UserRequest userRequest){
    return ResponseEntity.ok(authService.authenticate(userRequest));
  }
}
