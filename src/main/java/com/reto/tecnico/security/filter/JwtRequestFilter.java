package com.reto.tecnico.security.filter;

import com.reto.tecnico.security.services.JwtUtilService;
import io.jsonwebtoken.JwtException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
  private final JwtUtilService jwtUtilService;
  private final UserDetailsService userDetailsService;
  private final String typeMedia = "application/json";
  private List<String> pathsToSkipAuthorization = Arrays.asList("/auth/login", "/actuator/health", "/favicon", "/swagger-ui/index.html", "/swagger-ui", "/v3");

  public JwtRequestFilter(JwtUtilService jwtUtilService, UserDetailsService userDetailsService) {
    this.jwtUtilService = jwtUtilService;
    this.userDetailsService = userDetailsService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
          throws ServletException, IOException {
    try {
      String authorizationHeader = request.getHeader("Authorization");
      String email = null;
      String jwt = null;

      String requestURI = request.getRequestURI();

      if (shouldSkipAuthorization(requestURI)) {
        chain.doFilter(request, response);
        return;
      }

      if (authorizationHeader == null) {
        handleUnauthorizedRequest(response, "El Header Authorization es obligatorio.");
        return;
      }

      if (authorizationHeader.equals("Bearer") || authorizationHeader.equals("Bearer ")){
        handleUnauthorizedRequest(response, "Formato inválido de Token.");
        return;
      }

      if (authorizationHeader.startsWith("Bearer ")) {
        jwt = authorizationHeader.substring(7);
        email = jwtUtilService.extractUsername(jwt);
      }

      if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        authenticateUser(request, jwt, email);
      }
    } catch (JwtException ex) {
      handleUnauthorizedRequest(response, ex.getMessage());
      return;
    }

    chain.doFilter(request, response);
  }

  private boolean shouldSkipAuthorization(String requestURI) {
    return pathsToSkipAuthorization.stream().anyMatch(requestURI::startsWith);
  }

  private void handleUnauthorizedRequest(HttpServletResponse response, String message) throws IOException {
    response.setContentType(typeMedia);
    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    response.getWriter().println("{ \"message\": \"" + message + "\" }");
  }

  private void authenticateUser(HttpServletRequest request, String jwt, String email) {
    UserDetails userDetails = userDetailsService.loadUserByUsername(email);
    if (jwtUtilService.validateToken(jwt, userDetails)) {
      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
              userDetails, null, userDetails.getAuthorities());
      authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
      SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
  }
}
