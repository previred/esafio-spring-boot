package cl.previred.desafip.msprevireddesafio.controllers;

import cl.previred.desafip.msprevireddesafio.DTO.JwtResponse;
import cl.previred.desafip.msprevireddesafio.DTO.LoginRequest;
import cl.previred.desafip.msprevireddesafio.services.TokenService;
import cl.previred.desafip.msprevireddesafio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;



    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        if (userService.findUser(loginRequest)) {
            String token = tokenService.generateToken(loginRequest.getUsername());
            return ResponseEntity.ok(new JwtResponse(token));
        }else
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }
}
