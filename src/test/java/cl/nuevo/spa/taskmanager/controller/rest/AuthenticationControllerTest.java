package cl.nuevo.spa.taskmanager.controller.rest;

import cl.nuevo.spa.taskmanager.domain.dto.AuthenticationRequestDto;
import cl.nuevo.spa.taskmanager.domain.dto.UserDto;
import cl.nuevo.spa.taskmanager.service.AuthenticationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class AuthenticationControllerTest {

  @Mock private AuthenticationService authenticationService;

  @InjectMocks private AuthenticationController authenticationController;

  @Test
  void ShouldReturnUserDtoWhenLoginItsOk() {
    UserDto userDto = UserDto.builder().build();
    Mockito.when(authenticationService.login(Mockito.any(AuthenticationRequestDto.class)))
        .thenReturn(userDto);
    ResponseEntity<UserDto> loginResponse =
        authenticationController.login(AuthenticationRequestDto.builder().build());
    Assertions.assertEquals(HttpStatus.OK, loginResponse.getStatusCode());
    Assertions.assertEquals(userDto, loginResponse.getBody());
  }
}
