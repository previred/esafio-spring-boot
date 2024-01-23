package com.previred.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUserService userService;
    @Autowired
    private JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().matches("/auth/getTokenUsuario")){
            filterChain.doFilter(request,response);
        }else{
            final String requestTokenHeader = request.getHeader("Authorization");
            if (StringUtils.startsWith(requestTokenHeader,"Bearer ")) {
                String jwtToken = requestTokenHeader.substring(7);
                try {
                    String username = jwtUtil.getUsernameFromToken(jwtToken);
                    if (StringUtils.isNotEmpty(username)
                            && null == SecurityContextHolder.getContext().getAuthentication()) {
                        UserDetails userDetails = userService.loadUserByUsername(username);
                        if (jwtUtil.validateToken(jwtToken, userDetails)) {
                            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                                    new UsernamePasswordAuthenticationToken(
                                            userDetails, null, userDetails.getAuthorities());
                            usernamePasswordAuthenticationToken
                                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            SecurityContextHolder.getContext()
                                    .setAuthentication(usernamePasswordAuthenticationToken);
                        }
                    }
                } catch (IllegalArgumentException e) {
                    logger.error("No se pudo obtener el JWT Token");
                } catch (ExpiredJwtException e) {
                    logger.error("JWT Token ha expirado");
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            } else {
                logger.warn("JWT Token no comienza con Bearer");
            }
            filterChain.doFilter(request, response);
        }
    }
}
