package cl.tecnova.pruebatecnica.config;

import io.jsonwebtoken.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static cl.tecnova.pruebatecnica.config.SecurityConstans.*;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

   private Claims setSigningKey(HttpServletRequest request) {
      String jwtToken = request.getHeader(HEADER_AUTHORIZACION_KEY).replace(TOKEN_BEARER_PREFIX, "");

      return Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(jwtToken)
            .getPayload();
   }
   
   private void setAuthentication(Claims claims) {
      List<String> authorities = (List<String>) claims.get("authorities");
      UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
      SecurityContextHolder.getContext().setAuthentication(auth);
   }

   private boolean isJWTValid(HttpServletRequest request) {
      String authenticationHeader = request.getHeader(HEADER_AUTHORIZACION_KEY);
      return authenticationHeader != null && authenticationHeader.startsWith(TOKEN_BEARER_PREFIX);
   }

   @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      try {
         if (isJWTValid(request)) {
            Claims claims = setSigningKey(request);
            if (claims.get("authorities") != null) {
               setAuthentication(claims);
            } else {
               SecurityContextHolder.clearContext();
            }
         } else {
            SecurityContextHolder.clearContext();
         }
         filterChain.doFilter(request, response);
      } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
         response.setStatus(HttpServletResponse.SC_FORBIDDEN);
         response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
      }
   }

}