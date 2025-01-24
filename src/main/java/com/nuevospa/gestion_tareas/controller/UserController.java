package com.nuevospa.gestion_tareas.controller;


import com.nuevospa.gestion_tareas.entity.User;
import com.nuevospa.gestion_tareas.model.ApiResponseDetail;
import com.nuevospa.gestion_tareas.model.AuthRequest;
import com.nuevospa.gestion_tareas.service.JwtService;
import com.nuevospa.gestion_tareas.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticación y Gestión de Usuarios", description = "Este controlador maneja las operaciones relacionadas con la autenticación de usuarios y su gestión en el sistema, como la creación de nuevos usuarios y la generación de tokens JWT.")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Operation(
            summary = "Crear un nuevo usuario",
            description = "Este endpoint permite agregar un nuevo usuario al sistema. El usuario debe incluir su nombre, correo electrónico y contraseña en el cuerpo de la solicitud."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente",
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ApiResponseDetail.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor, al agregar el usuario."),
    })
    @PostMapping("/addNewUser")
    public ResponseEntity<ApiResponseDetail<String>> addNewUser(@RequestBody @Parameter(description = "Objeto que contiene los datos del nuevo usuario a agregar.") User user) {
        try {
            String result = service.addUser(user);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponseDetail<>("Usuario creado con éxito", result, true, HttpStatus.CREATED.value()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseDetail<>("Se produjo un error al agregar el usuario: " + ex.getMessage(), null, false, HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }



    @Operation(
            summary = "Generar Token de Autenticación",
            description = "Este endpoint permite a los usuarios autenticarse utilizando su nombre de usuario y contraseña. Si la autenticación es exitosa, se genera un token JWT que puede ser usado para autenticar futuras solicitudes."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token generado exitosamente",
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ApiResponseDetail.class))),
            @ApiResponse(responseCode = "401", description = "Autenticación fallida. Usuario no encontrado o credenciales incorrectas."),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.")
    })
    @PostMapping("/generateToken")
    public ResponseEntity<ApiResponseDetail<String>> authenticateAndGetToken(
            @RequestBody @Parameter(description = "Objeto con las credenciales del usuario (nombre de usuario y contraseña)") AuthRequest authRequest) {
        try {
            // Autenticar al usuario
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            // Si la autenticación es exitosa, se genera el token
            if (authentication.isAuthenticated()) {
                String token = jwtService.generateToken(authRequest.getUsername());
                return ResponseEntity.ok(
                        new ApiResponseDetail<>("Token generado exitosamente", token, true, HttpStatus.OK.value())
                );
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ApiResponseDetail<>("Solicitud de usuario no válida!", null, false, HttpStatus.UNAUTHORIZED.value()));
            }
        } catch (UsernameNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponseDetail<>("Usuario no encontrado: " + ex.getMessage(), null, false, HttpStatus.UNAUTHORIZED.value()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseDetail<>("Se produjo un error: " + ex.getMessage(), null, false, HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }


}
