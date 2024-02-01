package com.desafio.spring.desafiospringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.spring.desafiospringboot.model.EstadoTarea;
import com.desafio.spring.desafiospringboot.service.EstadoTareaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class EstadoTareaController {
	
	@Autowired
	private EstadoTareaService estadoTareaService;
	
	@Operation( summary = "Nos permite obtener los estados de las tareas", description = "Nos permite obtener los estados de las tareas desde la BD",
			parameters = {@Parameter(name="token", in=ParameterIn.HEADER, required=true)},
			tags = {"Servicios"} )
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "OK", 
					  		content = { @Content(schema = @Schema(implementation = String.class)) }),
			  @ApiResponse(responseCode = "400", description = "Los datos enviados son incorrectos o hay datos obligatorios no enviados", 
			    			content = @Content), 
			  @ApiResponse(responseCode = "401", description = "No hay autorización para llamar al servicio", 
								content = @Content), 
			  @ApiResponse(responseCode = "403", description = "Acceso denegado", 
								content = @Content), 
			  @ApiResponse(responseCode = "404", description = "No se encontrado información.", 
							content = @Content),
			  @ApiResponse(responseCode = "500", description = "Error en servidor.", 
			    			content = @Content) })
	@GetMapping("/obtenerEstadoTareas")
	public ResponseEntity<List<EstadoTarea>> obtenerEstadoTareas() {
		return estadoTareaService.obtenerEstadoTareas();
	}

}
