package com.company.configuration.security;

import com.company.exception.enums.CodeExceptions;
import com.company.model.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        var out = response.getOutputStream();
        new ObjectMapper().writeValue(
                out, ErrorResponse.builder()
                        .codError(CodeExceptions.NOT_AUTHORIZED.getValue())
                        .messageError("NOT_AUTHORIZED")
                        .build());
        out.flush();
    }
}
