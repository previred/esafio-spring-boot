package jlillor.ms.tasks.manager.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jlillor.ms.tasks.manager.dtos.LoginRequest;
import jlillor.ms.tasks.manager.dtos.MsResponse;
import jlillor.ms.tasks.manager.properties.MessageProperty;
import jlillor.ms.tasks.manager.services.AuthentificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * The Class AuthorizationController.
 *
 * @since 1.0
 * @version 1.0
 * @author Juan Lillo
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorizationController {

    /** The service. */
    private final AuthentificationService service;
    /** The message property. */
    private final MessageProperty messageProperty;

    // -----------------------------------------------------------------------------------------
    // -- Métodos Públicos ---------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------
    /**
     * Método que loguea un usuario, generando un token de sesión JWT.
     * @param user {@link LoginRequest} el usuario a loguear, con su email y password.
     * @return el token de sesión.
     */
    @ApiOperation("Método que loguea un usuario, generando un token de sesión JWT.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    @PostMapping("/login")
    public ResponseEntity<MsResponse<String>> loginUser(@Valid @RequestBody final LoginRequest user) {
        final var resp = new MsResponse<>(messageProperty.getSuccess(), service.login(user));
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }

}
