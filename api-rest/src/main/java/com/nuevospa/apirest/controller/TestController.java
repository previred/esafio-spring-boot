package com.nuevospa.apirest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "Test", description = "permite probar el JWT")
public class TestController {
	@Operation( summary = "Test", description = "Permite probar JWT")
	@ApiResponses(
			value = {
					@ApiResponse(responseCode = "200", description = "Operación exitosa", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = TaskStatusController.class)) }),
					@ApiResponse(responseCode = "403", description = "Error, no conectado" ,content = { @Content( mediaType = "none", schema = @Schema(implementation = HashMap.class))  } )
			})
	@PostMapping(value = "test")
	public String welcome()
	{
		return "jwt test successful";
	}
}