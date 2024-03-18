package com.nuevospa.taskmanager.ports.input;

import com.nuevospa.taskmanager.domain.model.Usuarios;
import com.nuevospa.taskmanager.domain.repository.UsuariosRepository;
import io.swagger.annotations.Api;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Usuarios Controller", description = "Operaciones relacionadas con usuarios")
@RestController
public class UsuariosController {

    private UsuariosRepository usuariosRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsuariosController(UsuariosRepository usuarioRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usuariosRepository = usuarioRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/users/")
    public void saveUsuario(@RequestBody Usuarios user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        usuariosRepository.save(user);
    }

    @GetMapping("/users/")
    public List<Usuarios> getAllUsuarios() {
        return usuariosRepository.findAll();
    }

    @GetMapping("/users/{username}")
    public Usuarios getUsuario(@PathVariable String username) {
        return usuariosRepository.findByUsername(username);
    }
}
