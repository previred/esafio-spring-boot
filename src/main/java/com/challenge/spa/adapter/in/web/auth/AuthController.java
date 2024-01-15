package com.challenge.spa.adapter.in.web.auth;

import com.challenge.spa.adapter.in.web.auth.resource.AuthResponse;
import com.challenge.spa.adapter.in.web.auth.resource.AuthRequest;
import com.challenge.spa.application.port.in.AuthenticationPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Authentication management APIs")
public class AuthController {

  private final AuthenticationPort authenticationPort;

  public AuthController(AuthenticationPort authenticationPort) {
    this.authenticationPort = authenticationPort;
  }

  @Operation(
          summary = "Authentication login",
          description = "Get a JWT token with credentials.")
  @ApiResponses({
          @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = AuthResponse.class), mediaType = "application/json") }),
          @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
          @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
  @PostMapping("login")
  public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
    return ResponseEntity.ok(AuthResponse
            .fromDomain(authenticationPort.authenticate(request.toUserDomain())));
  }

}
