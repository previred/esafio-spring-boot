package com.previred.challenge.filters;

import com.previred.challenge.dto.LoggedUserDTO;
import com.previred.challenge.exceptions.UserNotFoundException;
import com.previred.challenge.repositories.UserRepository;
import com.previred.challenge.services.JWTService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Slf4j
@Component
@RequiredArgsConstructor
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private static final String BEARER_HEADER = "Bearer ";

    @NonNull
    private final JWTService jwtService;

    @NonNull
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (requestTokenHeader != null && requestTokenHeader.startsWith(BEARER_HEADER)) {
            checkToken(requestTokenHeader.substring(BEARER_HEADER.length()));
        }

        filterChain.doFilter(request, response);
    }

    private void checkToken(String token) {
        String loggedUserEmail = jwtService.getUsernameFromToken(token);

        var registeredUser = userRepository.findByEmail(loggedUserEmail)
                .orElseThrow(() -> new UserNotFoundException(loggedUserEmail));

        if (!jwtService.validateJwtToken(token, registeredUser)) {
            throw new UserNotFoundException(loggedUserEmail);
        }

        var loggedUser = new LoggedUserDTO(registeredUser.getId(), registeredUser.getEmail());

        var auth = new UsernamePasswordAuthenticationToken(loggedUser, null, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

}
