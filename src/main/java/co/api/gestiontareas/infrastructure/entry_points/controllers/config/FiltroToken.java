package co.api.gestiontareas.infrastructure.entry_points.controllers.config;


import co.api.gestiontareas.domain.model.common.MensajeDTO;
import co.api.gestiontareas.infrastructure.helpers.jwt.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class FiltroToken extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (request.getMethod().equals("OPTIONS"))
            response.setStatus(HttpServletResponse.SC_OK);

        else {
            String requestURI = request.getRequestURI();
            String token = getToken(request);
            boolean error = true;
            try {
                if (requestURI.startsWith("/api/auth/")
                 || requestURI.startsWith("/h2"))  {
                    error = false;
                } else if (requestURI.startsWith("/api/task")) {
                    if (token != null) {
                        Jws<Claims> jws = jwtUtil.parseJwt(token);
                        if (!jws.getPayload().get("rol").equals("CLIENT")) {
                            crearRespuestaError("No autorizado, se requiere token JWT",
                                HttpServletResponse.SC_FORBIDDEN, response);
                        } else {
                            error = false;
                        }
                    } else {
                        crearRespuestaError("No autorizado, se requiere token JWT",

                            HttpServletResponse.SC_FORBIDDEN, response);

                    }
                }
            } catch (MalformedJwtException | SignatureException e) {
                crearRespuestaError("El token es incorrecto",
                        HttpServletResponse.SC_INTERNAL_SERVER_ERROR, response);

            } catch (ExpiredJwtException e) {
                crearRespuestaError("El token esta vencido",
                        HttpServletResponse.SC_INTERNAL_SERVER_ERROR, response);

            } catch (Exception e) {
                crearRespuestaError(e.getMessage(),
                        HttpServletResponse.SC_INTERNAL_SERVER_ERROR, response);

            }
            if (!error) {
                filterChain.doFilter(request, response);
            }
        }
    }

    private String getToken(HttpServletRequest req) {
        String header = req.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer "))
            return header.replace("Bearer ", "");
        return null;
    }

    private void crearRespuestaError(String mensaje, int codigoError, HttpServletResponse
            response) throws IOException {
        MensajeDTO<String> dto = new MensajeDTO<>(mensaje);
        response.setContentType("application/json");
        response.setStatus(codigoError);
        response.getWriter().write(new ObjectMapper().writeValueAsString(dto));
        response.getWriter().flush();
        response.getWriter().close();
    }
}

