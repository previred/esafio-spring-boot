package cl.dpichinil.desafio.desafiospringboot.config.security;

import cl.dpichinil.desafio.desafiospringboot.dto.JwtUser;
import cl.dpichinil.desafio.desafiospringboot.persistence.entity.User;
import cl.dpichinil.desafio.desafiospringboot.util.JwtService;
import cl.dpichinil.desafio.desafiospringboot.util.SecurityConstant;
import cl.dpichinil.desafio.desafiospringboot.util.SecurityFunction;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import cl.dpichinil.desafio.desafiospringboot.persistence.repository.UserRepository;

import java.io.IOException;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = SecurityFunction.getHeaderToken(request);
        String token = null, username = null;
        if (authHeader != null && authHeader.startsWith(SecurityConstant.PREFIX_TOKEN)) {
            token = SecurityFunction.quitaBarer(authHeader);
            username = jwtService.extractUsername(token);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Optional<User> optional = userRepository.findByUsername(username);
            if(optional.isPresent()){
                JwtUser jwtUser = SecurityFunction.parseUserToJwtUser(optional.get());
                if (jwtService.validateToken(token, jwtUser)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            jwtUser.getUsername(),
                            null,
                            jwtUser.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }


}
