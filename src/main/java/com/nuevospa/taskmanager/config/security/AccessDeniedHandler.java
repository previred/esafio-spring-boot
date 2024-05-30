package com.nuevospa.taskmanager.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nuevospa.taskmanager.dto.ErrorResponse;
import com.nuevospa.taskmanager.exceptions.enums.ExceptionEnum;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;


@Component
public class AccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        var out = response.getOutputStream();
        new ObjectMapper().writeValue(
                out, new ErrorResponse(
                        ExceptionEnum.FORBIDDEN.getName(),
                        "FORBIDDEN",
                        LocalDateTime.now().toString()
                )
        );
        out.flush();
    }
}
