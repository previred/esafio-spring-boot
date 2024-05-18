package co.com.task.api.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.task.api.dto.ErrorResponseDTO;
import co.com.task.api.dto.UserDTO;
import co.com.task.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
	this.userService = userService;
    }

    @Operation(summary = "Consulta todos los usuarios")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK", content = {
	    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserDTO.class))) }),
	    @ApiResponse(responseCode = "403", description = "Forbidden", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)) }),
	    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)) }) })
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
	return ResponseEntity.ok(userService.getAll());
    }

    @Operation(summary = "Consulta un usuario por ID")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Ok", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class)) }),
	    @ApiResponse(responseCode = "400", description = "Bad Request", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)) }),
	    @ApiResponse(responseCode = "403", description = "Forbidden", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)) }),
	    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)) }) })

    @GetMapping("/user-id/{idUser}")
    public ResponseEntity<UserDTO> getById(@PathVariable UUID idUser) {
	return ResponseEntity.ok(userService.getById(idUser));
    }

    @Operation(summary = "Realiza la creacion de un usuario")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "Created", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class)) }),
	    @ApiResponse(responseCode = "400", description = "Bad Request", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)) }),
	    @ApiResponse(responseCode = "403", description = "Forbidden", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)) }),
	    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)) }) })

    @PostMapping("/save")
    public ResponseEntity<UserDTO> save(@RequestBody @Valid UserDTO user) {
	return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @Operation(summary = "Realiza la actualizacion de un usuario")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Ok", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class)) }),
	    @ApiResponse(responseCode = "400", description = "Bad Request", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)) }),
	    @ApiResponse(responseCode = "403", description = "Forbidden", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)) }),
	    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)) }) })

    @PutMapping("/update")
    public ResponseEntity<UserDTO> update(@RequestBody @Valid UserDTO user) {
	return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
    }

    @Operation(summary = "Elimina un usuario por ID")
    @ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Ok", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)) }),
	    @ApiResponse(responseCode = "400", description = "Bad Request", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)) }),
	    @ApiResponse(responseCode = "403", description = "Forbidden", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)) }),
	    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
		    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)) }) })

    @DeleteMapping("/delete/{idUser}")
    public ResponseEntity<Void> delete(@PathVariable UUID idUser) {
	userService.delete(idUser);
	return ResponseEntity.ok().build();
    }

}
