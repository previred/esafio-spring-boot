package smarroquin.desafiospringboot.authentication;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
									HttpServletResponse response, 
									FilterChain filterChain) throws ServletException, IOException {
		
		String bearerToken = request.getHeader("Authorization");
		if (Objects.nonNull(bearerToken) && bearerToken.startsWith("Bearer ")) {
			String token = bearerToken.replace("Bearer ", "");
			
			UsernamePasswordAuthenticationToken upat = TokenConfiguration.getAuthentication(token);
			SecurityContextHolder.getContext().setAuthentication(upat);
		} else {
			SecurityContextHolder.clearContext();
		}
		
		filterChain.doFilter(request, response);
	}
}