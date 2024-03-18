package cl.nuevospa.gestortareas.controller;

import cl.nuevospa.gestortareas.api.UsuariosApi;
import cl.nuevospa.gestortareas.model.Usuarios;
import cl.nuevospa.gestortareas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController implements UsuariosApi {
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    
           /* Optional<Usuarios> usuario = usuarioService.findUsuario(id);
        return usuario.map(ResponseEntity::ok)
                .orElse(ResponseEntity
                        .notFound()
                        .build());
     */
}
