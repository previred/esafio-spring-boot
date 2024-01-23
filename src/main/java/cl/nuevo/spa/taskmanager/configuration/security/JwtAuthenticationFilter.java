package cl.nuevo.spa.taskmanager.configuration.security;

import static org.springframework.util.ObjectUtils.isEmpty;

import cl.nuevo.spa.taskmanager.service.JwtService;
import cl.nuevo.spa.taskmanager.service.UserDetailsAuthenticationService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private static final String START_AUTHORIZATION_STRING = "Bearer ";

  private final JwtService jwtService;

  private final UserDetailsAuthenticationService userDetailsAuthenticationService;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException {
    // Get authorization header and validate
    final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (isEmpty(header) || !header.startsWith(START_AUTHORIZATION_STRING)) {
      chain.doFilter(request, response);
      return;
    }
    final String token = header.split(" ")[1].trim();
    String userName = jwtService.extractUserName(token);
    UserDetails userDetails = userDetailsAuthenticationService.loadUserByUsername(userName);
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
        new UsernamePasswordAuthenticationToken(userName, null, userDetails.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    chain.doFilter(request, response);
  }
}
