package com.nuevospa.gestiontareas.rest.security;

import com.nuevospa.gestiontareas.auth.JwtTokenProvider;
import com.nuevospa.gestiontareas.auth.dto.JwtAuthenticationResponseDTO;
import com.nuevospa.gestiontareas.auth.dto.LoginRequestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtTokenProvider tokenProvider;
    @Mock
    private Authentication authentication;
    @InjectMocks
    private AuthController authController;

    @Test
    void authenticateUser_ShouldReturnJwtToken_WhenCredentialsAreValid() {
        // Prepare input and output
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO("user", "password");
        String expectedToken = "Bearer dummy.jwt.token";
        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        when(tokenProvider.generateToken(authentication)).thenReturn(expectedToken);

        // Call the method under test
        ResponseEntity<?> response = authController.authenticateUser(loginRequestDTO);

        // Assertions
        assertEquals(200, response.getStatusCodeValue());
        JwtAuthenticationResponseDTO responseBody = (JwtAuthenticationResponseDTO) response.getBody();
        assertEquals(expectedToken, responseBody.getAccessToken());
    }

    @Test
    void authenticateUser_ShouldReturnUnauthorized_WhenPasswordIsWrong() {
        // Prepare input
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO("wrong user", "a assword");

        // Simulate authentication failure
        when(authenticationManager.authenticate(any()))
                .thenThrow(new UsernameNotFoundException("Wrong user"));

        // Call the method under test
        assertThrows(UsernameNotFoundException.class, () -> authController.authenticateUser(loginRequestDTO));
    }
}
