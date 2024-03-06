package cl.previred.nuevospa.config.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import cl.previred.nuevospa.utils.ConstantsUtils;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            try {
                token = token.substring(7); 
                var algorithm = Algorithm.HMAC256(ConstantsUtils.SECRET_JWT); 
                var verifier = JWT.require(algorithm).build();
                var decodedJWT = verifier.verify(token);
                
                String username = decodedJWT.getSubject();
                
                var auth = new UsernamePasswordAuthenticationToken(username, null, null); 
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (JWTVerificationException e) {
                SecurityContextHolder.clearContext();
            }
        }
        filterChain.doFilter(request, response);
    }
}
