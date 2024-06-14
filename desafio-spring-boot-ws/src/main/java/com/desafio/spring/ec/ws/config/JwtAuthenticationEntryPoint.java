package com.desafio.spring.ec.ws.config;

import com.desafio.spring.ec.ws.exception.CustomErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        String exceptionMsg = (String) request.getAttribute("exception");
        exceptionMsg = exceptionMsg != null ? exceptionMsg : "Token not found or invalid";

        CustomErrorResponse errorResponse = new CustomErrorResponse(LocalDateTime.now(), exceptionMsg, request.getRequestURI());

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(convertObjectToJson(errorResponse));
    }

    private String convertObjectToJson(Object object) {
        try {
            return ObjectMapperProvider.getObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al convertir objeto a JSON", e);
        }
    }

    static class ObjectMapperProvider {
        private static final ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

        public static ObjectMapper getObjectMapper() {
            return mapper;
        }
    }
}