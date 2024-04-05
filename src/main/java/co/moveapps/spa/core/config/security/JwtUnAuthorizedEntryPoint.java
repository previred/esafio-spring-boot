package co.moveapps.spa.core.config.security;

import co.moveapps.spa.core.constants.TechnicalMessageResponseConstant;
import co.moveapps.spa.core.controller.dto.BaseResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtUnAuthorizedEntryPoint implements AuthenticationEntryPoint, AccessDeniedHandler {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        BaseResponse unauthorized = TechnicalMessageResponseConstant.EXCEPTION_UNAUTHORIZED;
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.sendError(unauthorized.getStatus().value(), unauthorized.getMessage());
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        BaseResponse unauthorized = TechnicalMessageResponseConstant.EXCEPTION_FORBIDDEN;
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.sendError(unauthorized.getStatus().value(), unauthorized.getMessage());

    }
}
