package com.nuevospa.apirest.controller;

import java.util.HashMap;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nuevospa.apirest.service.TaskStatusService;
import com.nuevospa.apirest.model.TaskStatus;

@RestController
@RequestMapping("api/taskStatus")
@RequiredArgsConstructor
@Tag(name = "Estados", description = "permite visualizar los estados de las tareas")
public class TaskStatusController {

	private final TaskStatusService taskStatusService;

	@Operation( summary = "Listado de los estados de las tareas", description = "Entrega un listado de todos los estados de las tareas")
	@ApiResponses(
			value = {
					@ApiResponse(responseCode = "200", description = "Operación exitosa", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = TaskStatusController.class)) }),
					@ApiResponse(responseCode = "403", description = "Error, no pudo ser generado el listado" ,content = { @Content( mediaType = "none", schema = @Schema(implementation = HashMap.class))  } )
			})
	@GetMapping
	public List<TaskStatus> getAllTaskStatus(){
		return taskStatusService.getAllTaskStatus();
	}
	

}
