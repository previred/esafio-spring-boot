package cl.previred.desafio.controller;

import cl.previred.desafio.dto.LoginRequestDTO;
import cl.previred.desafio.dto.LoginResponseDTO;
import cl.previred.desafio.dto.UserRequestDTO;
import cl.previred.desafio.entity.UserEntity;
import cl.previred.desafio.service.AuthenticationService;
import cl.previred.desafio.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<String> register(@RequestBody UserRequestDTO registerUserDto) {
        UserEntity registeredUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok("User created");
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> authenticate(@RequestBody LoginRequestDTO login) {
        UserEntity authenticatedUser = authenticationService.authenticate(login);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        return ResponseEntity.ok(LoginResponseDTO.builder()
                .token(jwtToken)
                .expiresIn(jwtService.getExpirationTime())
                .build());
    }
}
