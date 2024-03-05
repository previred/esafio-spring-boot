package com.example.desafio.controller;

import com.example.desafio.model.entities.Usuario;
import com.example.desafio.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;
    @ApiOperation("Muestra lista de usuarios")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Se mostro la lista correctamente"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Sin autorizacion"),
            @ApiResponse(code = 500, message = "Error interno servidor")
    })
    @GetMapping
    public List<Usuario> listaUsuarios(){
        return usuarioService.listar();
    }
    @PostMapping
    public ResponseEntity<Usuario> crear(@Valid @RequestBody Usuario usuario, BindingResult result  ){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.crearUsuario(usuario));
    }

    @ApiOperation("Registra un usuario")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Se registro un usuario correctamente"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Sin autorizacion"),
            @ApiResponse(code = 500, message = "Error interno servidor")
    })
    @PostMapping("/registrar")
    public ResponseEntity<Usuario> registrar(@Valid @RequestBody Usuario usuario, BindingResult result  ){

        return crear(usuario, result);
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
