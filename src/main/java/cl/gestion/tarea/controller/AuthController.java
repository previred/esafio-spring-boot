package cl.gestion.tarea.controller;

import cl.gestion.tarea.model.AuthRequest;
import cl.gestion.tarea.model.AuthResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @GetMapping(value = "/healthcheck")
    public ResponseEntity<String> healthCheck() {
        return new ResponseEntity<>("{ \"respuesta\" : \"Estoy vivo !!!\"}", HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        if ("admin".equals(request.getUsername()) && "password".equals(request.getPassword())) {
            String token = Jwts.builder()
                    .setSubject(request.getUsername())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                    .signWith(Keys.hmacShaKeyFor("mi-secreto".getBytes()), SignatureAlgorithm.HS256)
                    .compact();
            return ResponseEntity.ok(new AuthResponse(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}