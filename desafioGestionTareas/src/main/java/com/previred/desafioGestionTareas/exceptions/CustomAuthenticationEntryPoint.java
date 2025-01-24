package com.previred.desafioGestionTareas.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.previred.desafioGestionTareas.dtos.ApiResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {
        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        ApiResponseDTO apiResponseDTO = new ApiResponseDTO(false, "No estás autenticado. Por favor, inicia sesión.", response.getStatus());
        String jsonResponse = new ObjectMapper().writeValueAsString(apiResponseDTO);

        response.getWriter().write(jsonResponse);
    }


}
