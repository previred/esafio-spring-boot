package com.nuevospa.app.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nuevospa.app.models.ResponseError;
import lombok.experimental.UtilityClass;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@UtilityClass
public final class AuthExceptionHandler {
    public static void handle(HttpServletResponse response, Exception e) throws IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        String errorMessage;
        if (e instanceof BadCredentialsException) {
            errorMessage = "Invalid username or password";
        } else if (e instanceof LockedException) {
            errorMessage = "Your account has been locked";
        } else {
            errorMessage = e.getMessage();
        }
        List<String> errors = Collections.singletonList(errorMessage);
        ResponseError responseErrorBody = new ResponseError(null, "Authentication error", errors);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), responseErrorBody);
    }
}