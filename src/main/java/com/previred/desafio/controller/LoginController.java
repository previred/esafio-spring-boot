package com.previred.desafio.controller;

import com.previred.desafio.enums.ECatalogo;
import com.previred.desafio.exceptions.CustomException;
import com.previred.desafio.model.LoginRq;
import com.previred.desafio.model.LoginRs;
import com.previred.desafio.security.JwtUtil;
import com.previred.desafio.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * LoginController.
 *
 * @author Jimmy Villa.
 * @version 1.0.0, 23-01-2025
 */

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<LoginRs> createAuthenticationToken(@RequestBody LoginRq loginRq) {
        UserDetails userDetails;

        try {
            userDetails = usuarioService.loadUserByUsername(loginRq.getUsername());
        } catch (Exception e) {
            throw new CustomException(
                ECatalogo.USUARIO_NO_EXISTE.getCode(),
                ECatalogo.USUARIO_NO_EXISTE.getMessage()
            );
        }

        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRq.getUsername(), loginRq.getPassword())
                                              );
        } catch (BadCredentialsException e) {
            throw new CustomException(
                ECatalogo.CONTRASENA_INVALIDA.getCode(),
                ECatalogo.CONTRASENA_INVALIDA.getMessage()
            );
        }

        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(new LoginRs(jwt));
    }
}
