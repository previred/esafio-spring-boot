package cl.previred.gestion.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.previred.gestion.dto.JwtResponse;
import cl.previred.gestion.dto.LoginRequest;
import cl.previred.gestion.model.User;
import cl.previred.gestion.security.JwtTokenProvider;
import cl.previred.gestion.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "AuthController", description = "Endpoints para autenticación")
@RestController
@RequestMapping("/api/auth") 
public class AuthController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        
        if (loginRequest == null || loginRequest.getUsername() == null || loginRequest.getPassword() == null) {
            return ResponseEntity.badRequest().body("Username o password no detectados");
        }

        User user = userService.findByUsername(loginRequest.getUsername());
        
        if (user == null || !passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales invalidas");
        }
        String token = jwtTokenProvider.generateToken(user.getUsername());
        JwtResponse response = new JwtResponse(user.getUsername(), user.getEmail(), token);
        
        return ResponseEntity.ok(response);
    }
}

