package org.openapitools.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.openapitools.dto.ErrorResponse;
import org.openapitools.exceptions.enums.ExceptionEnum;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

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
