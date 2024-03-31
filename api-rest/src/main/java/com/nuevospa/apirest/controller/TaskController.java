package com.nuevospa.apirest.controller;

import java.util.HashMap;
import java.util.List;

import com.nuevospa.apirest.Auth.AuthResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nuevospa.apirest.service.TaskService;
import com.nuevospa.apirest.model.Task;

@RestController
@RequestMapping("api/task")
@Tag(name = "Gestión de tareas", description = "Permite a los usuarios crear, actualizar, eliminar y listar tareas")
public class TaskController {
	
	@Autowired
	private final TaskService taskService;

	@Operation( summary = "Creación / Modificación de la tarea", description = "Permite crear o modificar una tarea dependiendo de si se indica el Id en el JSON de entrada")
	@ApiResponses(
			value = {
					@ApiResponse(responseCode = "200", description = "Operación exitosa", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = TaskController.class)) }),
					@ApiResponse(responseCode = "403", description = "Error, no pudo ser creada la tarea" ,content = { @Content( mediaType = "none", schema = @Schema(implementation = HashMap.class))  } )
			})
	@PostMapping
	public Task createTask(@RequestBody Task task) {
		return taskService.createTask(task);
	}

	@Operation( summary = "Listado de todas las tareas", description = "Entrega un listado de todas las tareas existentes")
	@ApiResponses(
			value = {
					@ApiResponse(responseCode = "200", description = "Operación exitosa", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = TaskController.class)) }),
					@ApiResponse(responseCode = "403", description = "Error, no pudo ser generado el listado" ,content = { @Content( mediaType = "none", schema = @Schema(implementation = HashMap.class))  } )
			})
	@GetMapping
	public List<Task> getAllTask(){
		return taskService.getAllTask();
	}

	@Operation( summary = "Busca una tarea por Id", description = "Busca una tarea por Id")
	@ApiResponses(
			value = {
					@ApiResponse(responseCode = "200", description = "Operación exitosa", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = TaskController.class)) }),
					@ApiResponse(responseCode = "403", description = "Error, no pudo ser encontrada la tarea" ,content = { @Content( mediaType = "none", schema = @Schema(implementation = HashMap.class))  } )
			})
	@GetMapping ("{id}")
	public Task searchTaskById(@PathVariable("id") Long id) {
		return taskService.getTaskById(id);
	}

	@Operation( summary = "Elimina una tarea por Id", description = "Borra una tarea del sistema dado un Id")
	@ApiResponses(
			value = {
					@ApiResponse(responseCode = "200", description = "Operación exitosa", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = TaskController.class)) }),
					@ApiResponse(responseCode = "403", description = "Error, no pudo ser eliminada la tarea" ,content = { @Content( mediaType = "none", schema = @Schema(implementation = HashMap.class))  } )
			})
	@DeleteMapping("{id}")
	public void deleteTaskById(@PathVariable("id") Long id) {
		taskService.deleteTask(id);
	}
	
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

	@Operation( summary = "Busca las tareas por Status", description = "Lista todas las tareas dado un Id de status")
	@ApiResponses(
			value = {
					@ApiResponse(responseCode = "200", description = "Operación exitosa", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = TaskController.class)) }),
					@ApiResponse(responseCode = "403", description = "Error, no pudo ser encontrada la tarea" ,content = { @Content( mediaType = "none", schema = @Schema(implementation = HashMap.class))  } )
			})
    @GetMapping("/status/{statusId}")
    public List<Task> getTasksByStatusId(@PathVariable Long statusId) {
        return taskService.getTasksByStatusId(statusId);
    }
	
}
