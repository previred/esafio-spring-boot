package cl.previred.taskmanagement.web.controller;

import cl.previred.taskmanagement.application.dto.response.RespuestaTokenDTO;
import cl.previred.taskmanagement.core.domain.exception.TokenException;
import cl.previred.taskmanagement.core.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<RespuestaTokenDTO> login(@RequestParam String username, @RequestParam String password) {
        RespuestaTokenDTO respuestaTokenDTO = authService.login(username, password);
        return new ResponseEntity<>(respuestaTokenDTO, HttpStatus.OK);
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        String token = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
        }
        else {
            throw new TokenException("El token está mal formado");
        }

        if (token == null) {
            throw new TokenException("El token no puede ser nulo");
        }

        RespuestaTokenDTO respuestaTokenDTO = authService.validateToken(token);
        return new ResponseEntity<>(respuestaTokenDTO, HttpStatus.OK);
    }
}
