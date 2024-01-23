package cl.nuevo.spa.taskmanager.service;

import cl.nuevo.spa.taskmanager.domain.dto.AuthenticationRequestDto;
import cl.nuevo.spa.taskmanager.domain.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/** The type Authentication service. */
@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final AuthenticationManager authenticationManager;

  private final UserService userService;

  /**
   * Login user dto.
   *
   * @param authenticationRequestDto the authentication request dto
   * @return the user dto
   */
  public UserDto login(AuthenticationRequestDto authenticationRequestDto) {
    Authentication authentication =
        new UsernamePasswordAuthenticationToken(
            authenticationRequestDto.getUserName(), authenticationRequestDto.getPassword());
    authenticationManager.authenticate(authentication);
    return userService.getUserByUserName(authenticationRequestDto.getUserName());
  }
}
