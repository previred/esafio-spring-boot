package cl.previred.challenge.controller;

import cl.previred.challenge.api.AuthApi;
import cl.previred.challenge.dto.AuthLoginPost200Response;
import cl.previred.challenge.dto.AuthLoginPostRequest;
import cl.previred.challenge.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthApi {

  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;

  @Autowired
  public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
    this.authenticationManager = authenticationManager;
    this.jwtService = jwtService;
  }

  @Override
  public ResponseEntity<AuthLoginPost200Response> authLoginPost(@RequestBody AuthLoginPostRequest authenticationRequest) {
    Authentication authenticate = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
    );
    UserDetails authenticateDetails = (UserDetails) authenticate.getPrincipal();
    final String jwt = jwtService.generateToken(authenticateDetails);
    AuthLoginPost200Response response = new AuthLoginPost200Response();
    response.setJwt(jwt);

    return ResponseEntity.ok().body(response);
  }
}
