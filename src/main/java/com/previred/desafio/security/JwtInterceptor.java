package com.previred.desafio.security;

import com.previred.desafio.enums.ECatalogo;
import com.previred.desafio.exceptions.CustomException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * JwtInterceptor.
 *
 * @author Jimmy Villa.
 * @version 1.0.0, 23-01-2025
 */

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new CustomException(
                ECatalogo.ERROR_TOKEN.getCode(),
                "Falta el token de autorización en el header."
            );
        }

        String token = authorizationHeader.substring(7); // Remueve "Bearer "

        try {
            if (!jwtUtil.isTokenExpired(token)) {
                return true; // Si el token es válido, continúa con la ejecución
            } else {
                throw new CustomException(
                    ECatalogo.ERROR_TOKEN.getCode(),
                    "El token de autorización ha expirado."
                );
            }
        } catch (Exception e) {
            throw new CustomException(
                ECatalogo.ERROR_TOKEN.getCode(),
                "Token de autorización no válido."
            );
        }
    }
}
