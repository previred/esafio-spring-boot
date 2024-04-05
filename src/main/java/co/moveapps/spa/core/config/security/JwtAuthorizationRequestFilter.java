package co.moveapps.spa.core.config.security;

import co.moveapps.spa.core.config.security.dto.CurrentUserPrincipal;
import co.moveapps.spa.core.constants.HttpCustomConstant;
import co.moveapps.spa.core.service.auth.AuthenticationService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthorizationRequestFilter extends OncePerRequestFilter {

    private final AuthenticationService authenticationService;

    public JwtAuthorizationRequestFilter(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        Boolean isAuthentication = Boolean.FALSE;
        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorization != null && authorization.startsWith(HttpCustomConstant.BEARER_AUTHORIZATION)) {
            final String accessToken = authorization.substring(7);
            try {
                CurrentUserPrincipal principal = authenticationService.validate(accessToken);
                if (principal != null) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(principal, null, Collections.emptyList());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    isAuthentication = Boolean.TRUE;
                }
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
        }

        if (!isAuthentication)
            SecurityContextHolder.clearContext();

        chain.doFilter(request, response);
    }

}
