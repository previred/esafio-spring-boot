package com.nuevospa.taskmanager.ports.input;

import com.nuevospa.taskmanager.application.UsuarioDetailsServiceImpl;
import com.nuevospa.taskmanager.domain.dto.UsuarioDTO;
import com.nuevospa.taskmanager.domain.model.Usuarios;
import com.nuevospa.taskmanager.ports.input.security.jwt.JWTAuthenticationConfig;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Login Controller", description = "Operaciones relacionadas con el login.")
@RestController
public class LoginController {

    @Autowired
    JWTAuthenticationConfig jwtAuthenticationConfig;

    @Autowired
    UsuarioDetailsServiceImpl usuarioDetailsServiceImpl;

    @PostMapping("login")
    public UsuarioDTO login(
            @RequestParam("user") String username,
            @RequestParam("pass") String pass) {

        Usuarios usuario = usuarioDetailsServiceImpl.findByUsernameAndPassword(username, pass);

        if (usuario == null) {
            return null;
        }
        
        String token = jwtAuthenticationConfig.getJWTToken(username);
        return new UsuarioDTO (username, pass ,token);

    }

}
