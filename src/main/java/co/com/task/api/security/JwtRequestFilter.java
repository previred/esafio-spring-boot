package co.com.task.api.security;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import co.com.task.api.dto.ErrorResponseDTO;
import co.com.task.api.service.JwtUtilService;
import co.com.task.api.utility.Utilities;
import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	private UserDetailsService userDetailsService;
	private JwtUtilService jwtUtilService;

	public JwtRequestFilter(UserDetailsService userDetailsService, JwtUtilService jwtUtilService) {
	    this.userDetailsService = userDetailsService;
	    this.jwtUtilService = jwtUtilService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String authorizationHeader = request.getHeader("Authorization");
		String username = null;
		String jwt = null;

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jwt = authorizationHeader.substring(7);
			try {
			    username = jwtUtilService.extractUsername(jwt);
			} catch (ExpiredJwtException ex) {
			    response.setStatus(HttpStatus.UNAUTHORIZED.value());
			    response.getWriter()
				    .write(ErrorResponseDTO.builder().code(HttpStatus.FORBIDDEN.value())
					    .message("Token JWT expirado")
					    .timestamp(Utilities.convertToDateViaInstant(LocalDateTime.now())).build()
					    .toString());
			    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			    return;
			}
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

		    UserDetails userDetails = null;
		    try {
			userDetails = this.userDetailsService.loadUserByUsername(username);
		    } catch (UsernameNotFoundException ex) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.getWriter().write(ErrorResponseDTO.builder().code(HttpStatus.FORBIDDEN.value())
				.message("El usuario ".concat(ex.getMessage())
					.concat(" asociado al token no existe"))
				.timestamp(Utilities.convertToDateViaInstant(LocalDateTime.now())).build().toString());
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			return;
		    }

			if (jwtUtilService.validateToken(jwt, userDetails)) {

				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}

		chain.doFilter(request, response);
	}

}
