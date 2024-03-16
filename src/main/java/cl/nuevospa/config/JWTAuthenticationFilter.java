package cl.nuevospa.config;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.nuevospa.application.usecase.UserDetailCustom;
import cl.nuevospa.domain.login.AuthCredentials;
import cl.nuevospa.domain.login.AuthResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private JWTGenerator jwtGenerator;
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
    
        AuthCredentials authCredentials = new AuthCredentials();
        
        try {
            authCredentials = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
        UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(authCredentials.getUsername(),
                                                            authCredentials.getPassword()
                                                            , Collections.emptyList());
        return getAuthenticationManager().authenticate(usernamePAT);
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        UserDetailCustom userCustom = (UserDetailCustom) authResult.getPrincipal();
        jwtGenerator = new JWTGenerator();
        String token = jwtGenerator.generateToken(userCustom.getUsername());
        response.addHeader("Authorization", "Bearer "+token);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setStatus(200);
        authResponse.setToken(token);
        ObjectMapper mapper = new ObjectMapper();
  
        String json = mapper.writeValueAsString(authResponse);
        response.setContentType("application/json");
        response.getWriter().write(json);
        response.getWriter().flush();
        super.successfulAuthentication(request, response, chain, authResult);
    }
}
