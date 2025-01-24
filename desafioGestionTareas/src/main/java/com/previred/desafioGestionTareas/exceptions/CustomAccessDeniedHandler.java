package com.previred.desafioGestionTareas.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.previred.desafioGestionTareas.dtos.ApiResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException {
        response.setContentType("application/json");
        response.setStatus(HttpStatus.FORBIDDEN.value());

        ApiResponseDTO apiResponseDTO = new ApiResponseDTO(false, "No tienes permisos para acceder a este recurso.", response.getStatus());
        String jsonResponse = new ObjectMapper().writeValueAsString(apiResponseDTO);

        response.getWriter().write(jsonResponse);
    }


}
