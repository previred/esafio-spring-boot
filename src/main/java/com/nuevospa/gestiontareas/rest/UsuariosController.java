package com.nuevospa.gestiontareas.rest;

import com.nuevospa.gestiontareas.auth.dto.UsuarioDTO;
import com.nuevospa.gestiontareas.exception.BadRequestException;
import com.nuevospa.gestiontareas.service.UsuariosService;
import com.nuevospa.gestiontareas.util.SecurityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/v1")
public class UsuariosController {
    private final UsuariosService usuariosService;

    public UsuariosController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @GetMapping("/usuarios")
    public ResponseEntity<Set<UsuarioDTO>> obtenerTodosLosUsuarios() {
        return ResponseEntity.ok(usuariosService.obtenerTodosLosUsuarios());
    }

    @GetMapping("/usuarios/{email}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorEmail(@PathVariable(name = "email") String email) {
        if (SecurityUtils.isValidEmail(email)) {
            final String sEmail = SecurityUtils.sanitizeString(email);
            return ResponseEntity.ok(usuariosService.obtenerUsuarioPorEmail(sEmail));
        } else {
            throw new BadRequestException("invalid email");
        }
    }
}
