package com.nuevospa.gestiontareas.rest;

import com.nuevospa.gestiontareas.auth.dto.UsuarioDTO;
import com.nuevospa.gestiontareas.exception.BadRequestException;
import com.nuevospa.gestiontareas.service.UsuariosService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuariosControllerTest {
    @Mock
    private UsuariosService usuariosService;
    @InjectMocks
    private UsuariosController usuariosController;

    @Test
    void obtenerTodosLosUsuarios_ShouldReturnUsuarios() {
        // Setup our service to return a specific value
        Set<UsuarioDTO> expectedUsuarios = Collections.singleton(new UsuarioDTO());
        when(usuariosService.obtenerTodosLosUsuarios()).thenReturn(expectedUsuarios);

        // Execute the method being tested
        ResponseEntity<Set<UsuarioDTO>> response = usuariosController.obtenerTodosLosUsuarios();

        // Validate the response
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedUsuarios, response.getBody());

        // Verify interaction with the mock
        verify(usuariosService).obtenerTodosLosUsuarios();
    }

    @Test
    void obtenerUsuarioPorEmail_ShouldReturnUsuario_WhenEmailIsValid() {
        // Assume that the email is valid and sanitized
        String email = "test@example.com";
        UsuarioDTO expectedUsuario = new UsuarioDTO();
        when(usuariosService.obtenerUsuarioPorEmail(email)).thenReturn(expectedUsuario);

        // Execute the method being tested
        ResponseEntity<UsuarioDTO> response = usuariosController.obtenerUsuarioPorEmail(email);

        // Validate the response
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedUsuario, response.getBody());

        // Verify interaction with the mock
        verify(usuariosService).obtenerUsuarioPorEmail(email);
    }

    @Test
    void obtenerUsuarioPorEmail_ShouldThrowBadRequestException_WhenEmailIsInvalid() {
        // Assume that the email is invalid
        String invalidEmail = "invalidemail";

        // Execute the method and expect an exception
        Exception exception = assertThrows(BadRequestException.class, () -> {
            usuariosController.obtenerUsuarioPorEmail(invalidEmail);
        });

        // Verify the exception message
        assertTrue(exception.getMessage().contains("invalid email"));

        // Verify there's no interaction with the service
        verify(usuariosService, never()).obtenerUsuarioPorEmail(anyString());
    }
}
