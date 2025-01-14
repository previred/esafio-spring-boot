package com.previred.gestion_tareas_api.exceptions;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.previred.gestion_tareas_api.dtos.ApiResponseDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
