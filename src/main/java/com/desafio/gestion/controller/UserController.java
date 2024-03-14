package com.desafio.gestion.controller;

import com.desafio.gestion.dto.UserDTO;
import com.desafio.gestion.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Usuario", description = "Administración API Usuario")
@RestController
@RequestMapping("/gestion/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    @Operation(
            summary = "Obtener usuario",
            description = "Obtener un usuario por nombre")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Usuario con el nombre dado no fue encontrado.", content = { @Content(schema = @Schema()) })
    })
    public ResponseEntity<UserDTO> getUserByUsername(@Parameter(description = "Nombre de usuario") @PathVariable String username) {
        UserDTO user = userService.findByUsername(username);
        return ResponseEntity.ok(user);
    }

    @GetMapping()
    @Operation(
            summary = "Obtener usuarios",
            description = "Obtener todos los usuarios")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(array = @ArraySchema(schema = @Schema(implementation = UserDTO.class)), mediaType = "application/json") })
    })
    public ResponseEntity<List<UserDTO>> getAll() {
        List<UserDTO> users = userService.findAll();
        return ResponseEntity.ok(users);
    }
}