package cl.rreyes.nuevospa.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import cl.rreyes.nuevospa.service.JwtTokenProvider;
import io.jsonwebtoken.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@WebFilter(urlPatterns = "/api/*") // Filtra las rutas de la API
public class JwtAuthFilter extends OncePerRequestFilter {
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException, java.io.IOException {
		// Obtener el token JWT de la cabecera
		String token = getJwtFromRequest(request);

		if (token != null && jwtTokenProvider.validateToken(token)) {
			String username = jwtTokenProvider.getUsernameFromToken(token);

			// Crear la autenticación
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null,
					null);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		// Continuar con el filtro
		filterChain.doFilter(request, response);
	}

	// Obtener el token de la cabecera Authorization
	private String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}
}
