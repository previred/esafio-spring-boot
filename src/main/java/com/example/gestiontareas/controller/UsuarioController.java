package com.example.gestiontareas.controller;

import com.example.gestiontareas.domain.model.Usuario;
import com.example.gestiontareas.domain.service.IUsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuario")
@Tag(name = "Usuarios")
@CrossOrigin
public class UsuarioController {

    private final IUsuarioService usuarioService;
    private final PasswordEncoder bcrypt;

    @Autowired
    public UsuarioController(IUsuarioService usuarioService, PasswordEncoder bcrypt){
        this.usuarioService = usuarioService;
        this.bcrypt = bcrypt;
    }

    @Operation(
            summary = "Lista de todos los usuarios"
    )
    @GetMapping("/usuarios")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Usuario>> listar(){

        List<Usuario> usuariosList = usuarioService.findAll();
        if (ObjectUtils.isEmpty(usuariosList)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(usuariosList, HttpStatus.OK);
    }

    @Operation(
            summary = "Obtiene un usuario en base a un ID"
    )
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Usuario> detalle(@PathVariable Long id){

        Usuario usuario = usuarioService.findById(id);
        if (ObjectUtils.isEmpty(usuario)) {
            return new ResponseEntity<>(usuario, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @Operation(
            summary = "Crea un usuario"
    )
    @PostMapping("/usuario")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario crear(@RequestBody Usuario usuario){

        usuario.setPassword(bcrypt.encode(usuario.getPassword()));
        return usuarioService.create(usuario);
    }


    @Operation(
            summary = "Elimina un usuario en base a un ID"
    )
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){

        if (ObjectUtils.isEmpty(usuarioService.findById(id))){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        usuarioService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
