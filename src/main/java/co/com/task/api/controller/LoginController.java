package co.com.task.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.task.api.dto.ErrorResponseDTO;
import co.com.task.api.dto.LoginRequestDTO;
import co.com.task.api.dto.LoginResponseDTO;
import co.com.task.api.service.SessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class LoginController {

    private final SessionService loginService;

    public LoginController(SessionService loginService) {
	this.loginService = loginService;
    }

    @Operation(summary = "Login usuario")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "OK", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponseDTO.class)) }),
	    @ApiResponse(responseCode = "400", description = "Bad Request", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)) }),
	    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)) }) })
    @PostMapping(value = "/authentication")
    public ResponseEntity<LoginResponseDTO> authentication(@RequestBody LoginRequestDTO login) {
	return ResponseEntity.ok(loginService.login(login));
    }

}
