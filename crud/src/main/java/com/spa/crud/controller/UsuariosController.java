package com.spa.crud.controller;

import com.spa.crud.dto.TareasDTO;
import com.spa.crud.dto.UsuariosDTO;
import com.spa.crud.exception.CrudException;
import com.spa.crud.model.Usuarios;
import com.spa.crud.service.UsuariosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Usuarios Controller", description = "Endpoints para Usuarios")
@RestController
@RequestMapping("/auth")
public class UsuariosController {

    @Autowired
    private UsuariosService userService;

    @Operation(summary = "Obtener Token de un usuario registrado",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(example = "{\"username\":\"user\",\"password\":\"pass\"}"))))
    @PostMapping("/login")
    public @ResponseBody UsuariosDTO getUser(@RequestBody Usuarios login){
        UsuariosDTO user = new UsuariosDTO();
        try {
            user = userService.loadUserByUser(login);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CrudException(e.getMessage());
        }
    }

    @Operation(summary = "Crear nuevo usuario",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(
                    example = "{\"nombre\":\"Jose Perez\",\"username\":\"jp32322\",\"password\":\"pass\",\"mail\":\"mail@mail.com\",\"role\":{\"nombre\":\"ADMIN o GENERAL\"}}"))))
    @PostMapping("/createuser")
    public  @ResponseBody String createNewUser(@RequestBody Usuarios login){
        try {
            login.setPassword(new BCryptPasswordEncoder().encode(login.getPassword()));
            userService.createNewUser(login);
            return "Nuevo usuario creado";
        } catch (Exception e) {
            e.printStackTrace();
            return "Existio un problema al crear nuevo usuario";
        }
    }

    @Operation(summary = "Buscar todos las Usuarios")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/findAllUsers")
    public @ResponseBody List<UsuariosDTO> getAllUsers(){
        List<UsuariosDTO> list = new ArrayList<>();
        try {
            list = userService.getAllUsers();
        } catch (Exception e) {
            throw new CrudException(e.getMessage());
        }
        return list;
    }

    @Operation(summary = "Borrar Usuario por username")
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/deleteUser/{username}")
    public @ResponseBody String deleteTaskById(@Parameter(description = "username del Usuario", required = true) @PathVariable(value="username") String username) {
        String message = null;
        try {
            userService.deleteUser(username);
            message = "Se ha eliminado el usuario con username: " + username;
        } catch (Exception e) {
            e.printStackTrace();
            message = "No se ha eliminado el usuario con username: " + username;
        }
        return message;
    }
}
