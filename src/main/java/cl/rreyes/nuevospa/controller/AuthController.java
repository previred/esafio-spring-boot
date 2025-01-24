package cl.rreyes.nuevospa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.rreyes.nuevospa.service.JwtTokenProvider;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	/* @Autowired
	    private AuthenticationManager authenticationManager;*/

	    @Autowired
	    private JwtTokenProvider jwtTokenProvider;
	    
	    @Value("${app.secret-key}")
	    private String secretKey;
/*
	    @PostMapping("/login")
	    public ResponseEntity<String> authenticate(@RequestParam String username, @RequestParam String password) {
	        // Autenticación del usuario
	        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

	        // Generar el token
	        String token = jwtTokenProvider.generateToken(username);

	        return ResponseEntity.ok(token);
	    }*/
	    
	    @PostMapping("/authorize")
	    public ResponseEntity<?> authorize(@RequestParam String secret, @RequestParam String username) {
	        if (!secret.equals(secretKey)) {
	            return ResponseEntity.status(401).body("Unauthorized");
	        }

	        String token = jwtTokenProvider.generateToken(username);
	        return ResponseEntity.ok(token);
	    }
}
