package com.nuevospa.app;

import com.nuevospa.app.providers.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class SetupTest {
    protected static final String SIGN_IN_ENDPOINT = "/api/v1/auth/sign-in";
    protected static final String TASK_ENDPOINT = "/api/v1/tasks";
    protected static final MediaType JSON_CONTENT_TYPE = MediaType.APPLICATION_JSON;
    private static final String AUTH_USERNAME = "sudo@vicente-munoz.cl";
    private static final String AUTH_PASSWORD = "demo123";
    @Autowired
    protected JwtTokenProvider jwtTokenProvider;
    protected String token;

    @BeforeEach
    void setUpToken() {
        Authentication authentication = new UsernamePasswordAuthenticationToken(AUTH_USERNAME, AUTH_PASSWORD);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        token = jwtTokenProvider.generateToken(authentication);
    }
}