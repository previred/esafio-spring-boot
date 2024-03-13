package cl.gestiontareasprevired.interceptor;

import cl.gestiontareasprevired.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtTokenInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // Extrae el token sin "Bearer "
            if (!jwtTokenUtil.validateTokenAndUser(token)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido o no autorizado");
                return false;
            }
            return true;
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Falta el token de autorización");
            return false; 
        }
    }
}
