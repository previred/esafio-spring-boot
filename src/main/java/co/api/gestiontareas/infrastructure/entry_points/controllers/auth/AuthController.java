package co.api.gestiontareas.infrastructure.entry_points.controllers.auth;


import co.api.gestiontareas.domain.model.common.MensajeDTO;
import co.api.gestiontareas.domain.model.user.User;
import co.api.gestiontareas.domain.service.AuthService;
import co.api.gestiontareas.infrastructure.entry_points.controllers.auth.request.LoginRequest;
import co.api.gestiontareas.infrastructure.entry_points.controllers.auth.response.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/auth")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Iniciar sesión", description = "", tags={ "Authentication" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inicio de sesión exitoso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoginResponse.class))),

            @ApiResponse(responseCode = "400", description = "Credenciales incorrectas"),

            @ApiResponse(responseCode = "500", description = "Error interno del servidor") })
    @RequestMapping(value = "/login",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<LoginResponse> login(@Parameter(in = ParameterIn.DEFAULT,
            description = "", required=true, schema=@Schema())
                                        @Valid @RequestBody LoginRequest body
    ) throws Exception{
        LoginResponse response= new LoginResponse(
                authService.login(body.username(), body.password()));

        return ResponseEntity.ok().body(
                response
        );
    }

    @Operation(summary = "Crear un nuevo usuario", description = "", tags={ "User" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)
                    )),

            @ApiResponse(responseCode = "400", description = "Parámetros faltantes o incorrectos"),

            @ApiResponse(responseCode = "500", description = "Error interno del servidor") })
    @RequestMapping(value = "/sign",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<Object> createUser(@RequestBody LoginRequest user) throws Exception {
        User userOptional = authService.createUser(user.username(),user.password());
        if (userOptional==null) {
            throw new Exception("No se pudo crear el usuario");
        }
        return ResponseEntity.ok().body(new MensajeDTO<>("Usuario creado exitosamente"));
    }
}

