package com.nuevospa.tareas.controller;

import com.nuevospa.tareas.entity.UsuarioEntity;
import com.nuevospa.tareas.model.AuthenticationRequest;
import com.nuevospa.tareas.model.Usuario;
import com.nuevospa.tareas.repository.UsuarioRepository;
import com.nuevospa.tareas.service.JwtUserDetailsService;
import com.nuevospa.tareas.service.UsuarioService;
import com.nuevospa.tareas.util.JwtUtil;
import com.nuevospa.tareas.util.ValidaPassword;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ValidaPassword validaPassword;

    @Operation(summary = "Obtener la lista de todos los usuarios")
    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioEntity>> listarUsuarios() {
        List<UsuarioEntity> usuarios = usuarioService.listarUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @Operation(summary = "Crear un nuevo usuario")
    @PostMapping("/crear")
    public ResponseEntity<Usuario> crearUsuario(@Valid @RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.crearUsuario(usuario);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un usuario existente")
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<UsuarioEntity> actualizarUsuario(@PathVariable Long id, @Valid @RequestBody Usuario usuarioActualizado) {
        UsuarioEntity usuario = usuarioService.actualizarUsuario(id, usuarioActualizado);
        if (usuario != null) {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Eliminar un usuario por ID")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        boolean eliminado = usuarioService.eliminarUsuario(id);
        if (eliminado) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Login para obtener un token JWT")
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody @Valid AuthenticationRequest authenticationRequest) throws Exception {
        UsuarioEntity usuario = usuarioRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow(() -> new Exception("Usuario no encontrado con el email: " + authenticationRequest.getEmail()));

        if (!validaPassword.verificarPassword(authenticationRequest.getPassword(), usuario.getPassword())) {
            throw new Exception("Contraseña incorrecta");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        // Crear encabezado con el token JWT
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwt);

        // Retornar la respuesta con el token en el encabezado
        return ResponseEntity.ok()
                .headers(headers)
                .body("AUTENTICADO CORRECTAMENTE");
    }
}
