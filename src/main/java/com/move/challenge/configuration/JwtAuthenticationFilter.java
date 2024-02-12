package com.move.challenge.configuration;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.move.challenge.utils.dto.GenericUserDetailsdto;
import com.move.challenge.utils.exception.ChallengeExceptionCode;
import com.move.challenge.utils.exception.InvalidCredentialsException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

   @Value("${jwt.secret}")
   private String jwtSecret;

   @Value("${jwt.expiration}")
   private int jwtExpirationMs;

   @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
         throws ServletException, IOException {

      final String authorizationHeader = request.getHeader("Authorization");

      String username = null;
      String jwtToken = null;

      if (Objects.nonNull(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
         jwtToken = authorizationHeader.substring(7);
         try {
            Claims body = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwtToken).getBody();
            username = body.get("subject").toString();
         } catch (Exception e) {
            logger.error("Error al validar el token: {}", e);
         }
      }

      if (Objects.nonNull(username) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
         // Si el token es válido y la autenticación no está establecida, configúrala manualmente
         UserDetails userDetails = new GenericUserDetailsdto(username, "secret"); // Implementa una clase UserDetails con los detalles del usuario
         UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
               userDetails, null, userDetails.getAuthorities());
         authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
         SecurityContextHolder.getContext().setAuthentication(authentication);
      }

      filterChain.doFilter(request, response);
   }
}