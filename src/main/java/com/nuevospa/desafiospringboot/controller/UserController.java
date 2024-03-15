package com.nuevospa.desafiospringboot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nuevospa.desafiospringboot.dto.UserDTO;
import com.nuevospa.desafiospringboot.model.User;
import com.nuevospa.desafiospringboot.service.IUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {


    @Autowired
    private IUserService userService;

    @PostMapping
    @Operation(summary = "Crear un nuevo usuario",
    description = "Guarda un nuevo usuario en la base de datos. La contraseña se encripta antes de guardar.",
    responses = {
        @ApiResponse(responseCode = "200", description = "Usuario creado exitosamente",
            content = @Content(schema = @Schema(implementation = UserDTO.class))),
        @ApiResponse(responseCode = "400", description = "La contraseña debe tener más de 8 caracteres e incluir al menos una mayúscula"),
        @ApiResponse(responseCode = "400", description = "Detalles del usuario proporcionados inválidos"),
        @ApiResponse(responseCode = "409", description = "El usuario ya existe")
    },
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Datos del nuevo usuario",
        required = true,
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = User.class),
            examples = @ExampleObject(
                name = "EjemploNuevoUsuario",
                summary = "Ejemplo de creación de usuario",
                description = "Ejemplo completo de cómo crear un nuevo usuario con nombre de usuario y contraseña.",
                value = "{\"username\": \"felipeB\", \"password\": \"12345678B\"}"
            )
        )
    ))
    public ResponseEntity<UserDTO> save(@RequestBody User user) {       
       return ResponseEntity.ok(userService.save(user));
    }
}
