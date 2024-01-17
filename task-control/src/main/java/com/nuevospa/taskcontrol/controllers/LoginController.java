package com.nuevospa.taskcontrol.controllers;

import com.nuevospa.taskcontrol.dtos.responses.LoginResponse;
import com.nuevospa.taskcontrol.services.UsuarioLoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task-control")
public class LoginController {

    private final UsuarioLoginService usuarioLoginService;

    @Autowired
    public LoginController(UsuarioLoginService usuarioLoginService) {
        this.usuarioLoginService = usuarioLoginService;
    }

    @Operation(summary = "Obtener un token de acceso mediante nombre y clave de usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado")})
    @GetMapping("/login/{nombre}/{clave}")
    @ResponseBody
    public ResponseEntity<LoginResponse> login(@PathVariable String nombre, @PathVariable String clave) {
        return ResponseEntity.ok(usuarioLoginService.loginUsuario(nombre, clave));
    }
}
