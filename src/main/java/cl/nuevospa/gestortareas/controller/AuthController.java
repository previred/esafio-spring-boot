package cl.nuevospa.gestortareas.controller;

import cl.nuevospa.gestortareas.api.AuthApi;
import cl.nuevospa.gestortareas.model.AuthPost200Response;
import cl.nuevospa.gestortareas.model.AuthPostRequest;
import cl.nuevospa.gestortareas.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthApi {
    private final UsuariosService usuariosService;
    private final AuthenticationManager authManager;

    @Autowired
    public AuthController(UsuariosService usuariosService, AuthenticationManager authManager) {
        this.usuariosService = usuariosService;
        this.authManager = authManager;
    }

    /**
     * Authenticates the user and returns the response entity with the authentication result.
     *
     * @param request the authentication request containing username and password
     * @return the response entity with the authentication result
     */
    @Override
    public ResponseEntity<AuthPost200Response> authPost(AuthPostRequest request) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        return ResponseEntity.ok(usuariosService.login((UserDetails) authentication.getPrincipal()));
    }
}
