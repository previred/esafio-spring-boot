package com.arturo.desafio_spring_boot.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.arturo.desafio_spring_boot.helpers.ErrorResponseObjectHelper;
import com.arturo.desafio_spring_boot.helpers.JwtHelper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import java.io.IOException;
import java.util.List;

@Component
@AllArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private JwtHelper jwtHelper;
    private UserDetailsService userDetailsService;
    private final ErrorResponseObjectHelper errorResponseObjectHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            boolean isPublic = this.publicPaths().stream().anyMatch(path -> request.getRequestURI().startsWith(path));
            if (isPublic) {
                filterChain.doFilter(request, response);
                return;
            }
            String headerAuth = request.getHeader("Authorization");
            if (headerAuth == null) {
                throw new MalformedJwtException("No Authorization header");
            }
            String token = this.jwtHelper.getAccessTokenFromHeader(request.getHeader("authorization"));
            if (token.isBlank()) {
                throw new MalformedJwtException("Bad auth header");
            }
            final var isValidToken = this.jwtHelper.validateToken(token);
            if (!isValidToken) {
                throw new MalformedJwtException("Invalid token");
            }
            final var usuario = this.jwtHelper.getUsernameFromToken(token);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    
            if (usuario != null && authentication == null) {
                final var userDetails = this.userDetailsService.loadUserByUsername(usuario);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch (MalformedJwtException | ExpiredJwtException | SignatureException e) {
            setInvalidTokenErrorResponse(response);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void setInvalidTokenErrorResponse(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write(
            this.errorResponseObjectHelper.toJsonString(
                HttpServletResponse.SC_UNAUTHORIZED,
                "Token inválido o expirado",
                "Unauthorized 'Token inválido o expirado'"
            )
        );
    }

    public List<String> publicPaths(){
        return List.of("/auth", "/swagger-ui", "/v3/api-docs");
    }

}

