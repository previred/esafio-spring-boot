package com.challenge.spa.adapter.in.web.auth;

import com.challenge.spa.adapter.in.web.auth.resource.AuthResponse;
import com.challenge.spa.adapter.in.web.auth.resource.AuthRequest;
import com.challenge.spa.application.port.in.AuthenticationPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthenticationPort authenticationPort;

  public AuthController(AuthenticationPort authenticationPort) {
    this.authenticationPort = authenticationPort;
  }

  @PostMapping("login")
  public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
    return ResponseEntity.ok(AuthResponse
            .fromDomain(authenticationPort.authenticate(request.toUserDomain())));
  }

}
