package cl.nuevo.spa.taskmanager.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cl.nuevo.spa.taskmanager.domain.dto.AuthenticationRequestDto;
import cl.nuevo.spa.taskmanager.domain.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

  private static final String USER_NAME = "USER_NAME";
  private static final String PASSWORD = "PASSWORD";
  @InjectMocks AuthenticationService authenticationService;
  @Mock private AuthenticationManager authenticationManager;
  @Mock private UserService userService;

  @Test
  void login() {
    UserDto userDto = buildUserDto();
    when(userService.getUserByUserName(anyString())).thenReturn(userDto);
    when(authenticationManager.authenticate(any(Authentication.class)))
        .thenReturn(mock(Authentication.class));
    AuthenticationRequestDto authenticationRequestDto =
        spy(AuthenticationRequestDto.builder().password(PASSWORD).userName(USER_NAME).build());
    Assertions.assertEquals(
        userDto, authenticationService.login(authenticationRequestDto), "should return userDto");
    verify(authenticationRequestDto, times(2)).getUserName();
    verify(authenticationRequestDto, times(1)).getPassword();
    verify(authenticationManager, times(1)).authenticate(any(Authentication.class));
  }

  private UserDto buildUserDto() {
    return UserDto.builder().build();
  }
}
