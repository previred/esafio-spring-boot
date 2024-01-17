package cl.previred.challenge.config.security;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtAuthenticationFilter extends GenericFilterBean {

    private JwtManager jwtManager;

    public JwtAuthenticationFilter(JwtManager jwtManager) {
        this.jwtManager = jwtManager;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        String tokenAttr = ((HttpServletRequest)servletRequest).getHeader("Authorization");

        try {
            if(tokenAttr != null && !tokenAttr.isEmpty()){
                String token = tokenAttr.substring("Bearer ".length() -1);
                JwtUserData userData = jwtManager.parseJwtUserData(token);

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userData, null, null);
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails((HttpServletRequest) servletRequest));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }catch (ExpiredJwtException ex) {}

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
