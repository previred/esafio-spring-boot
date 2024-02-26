package com.springboot.desafio.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.desafio.constants.Constantes;
import com.springboot.desafio.services.impl.UserDetailsServiceImpl;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.springboot.desafio.constants.Constantes.*;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        try{
            final String authorizationHeader = request.getHeader(AUTHORIZATION);

            String username = null;
            String jwt = null;

            if (authorizationHeader != null && authorizationHeader.startsWith(BEARER + ESPACIO)) {
                jwt = authorizationHeader.substring(7);
                username = extractUsername(jwt);
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                if (validateToken(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
            chain.doFilter(request, response);
        }catch(Exception e){
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpServletResponse.setContentType("application/json");
            Map<String, Object> body = new HashMap<>();
            body.put("mensaje", "Token Inválido." );
            String responseBody = new ObjectMapper().writeValueAsString(body);
            httpServletResponse.getWriter().write(responseBody);
            return;
        }
    }

    private String extractUsername(String token) {
        return Jwts.parser().setSigningKey(Constantes.SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    private boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = Jwts.parser().setSigningKey(Constantes.SECRET_KEY).parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }
}
