package com.nuevospa.desafiospringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.nuevospa.desafiospringboot.dto.TaskDTO;
import com.nuevospa.desafiospringboot.model.Task;
import com.nuevospa.desafiospringboot.service.ITaskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private ITaskService taskService;

	@PostMapping
	@Operation(summary = "Crea una nueva tarea", description = "Agrega una nueva tarea a la lista. Requiere especificar un usuario y un estado de tarea existentes.", responses = {
			@ApiResponse(description = "Tarea creada exitosamente", responseCode = "201"),
			@ApiResponse(description = "Estado de la tarea no encontrado", responseCode = "404"),
			@ApiResponse(description = "Usuario no encontrado", responseCode = "404") }, requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Ejemplo de creación de tarea", required = true, content = @Content(schema = @Schema(implementation = TaskDTO.class), examples = @ExampleObject(name = "EjemploCrearTarea", summary = "Ejemplo de creación de tarea", description = "Ejemplo completo de cómo crear una nueva tarea especificando título, descripción, estado y usuario.", value = """
					{
					  "title": "Implementar nueva funcionalidad 1",
					  "description": "Desarrollar la nueva funcionalidad siguiendo los requisitos especificados en el documento de diseño.",
					  "statusId": 1,
					  "userId": 1
					}
					"""))))
	public ResponseEntity<TaskDTO> createTask(@RequestBody Task task) {
		TaskDTO newTask = taskService.saveTask(task);
		return new ResponseEntity<>(newTask, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Obtiene una tarea por ID", description = "Devuelve una tarea específica por su ID. Lanza una excepción si la tarea no existe.", responses = {
			@ApiResponse(description = "Tarea encontrada", responseCode = "200", content = @Content(schema = @Schema(implementation = TaskDTO.class))),
			@ApiResponse(description = "Tarea no encontrada", responseCode = "404") })
	public ResponseEntity<TaskDTO> getTaskById(@PathVariable int id) {
		TaskDTO task = taskService.getTaskById(id);
		return ResponseEntity.ok(task);
	}

	@GetMapping
	@Operation(summary = "Lista todas las tareas", description = "Devuelve una lista de todas las tareas disponibles.", responses = {
			@ApiResponse(description = "Operación exitosa", responseCode = "200", content = @Content(schema = @Schema(implementation = List.class))) })
	public ResponseEntity<List<TaskDTO>> getAllTasks() {
		List<TaskDTO> tasks = taskService.getAllTasks();
		return ResponseEntity.ok(tasks);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Actualiza una tarea", description = "Actualiza los detalles de una tarea existente por ID. Verifica la existencia del estado de la tarea y del usuario.", responses = {
			@ApiResponse(description = "Tarea actualizada exitosamente", responseCode = "200", content = @Content(schema = @Schema(implementation = TaskDTO.class))),
			@ApiResponse(description = "Tarea no encontrada", responseCode = "404"),
			@ApiResponse(description = "Estado de la tarea no encontrado", responseCode = "404"),
			@ApiResponse(description = "Usuario no encontrado", responseCode = "404") }, requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Ejemplo de actualización de tarea", required = true, content = @Content(schema = @Schema(implementation = Task.class), examples = @ExampleObject(name = "EjemploActualizarTarea", summary = "Ejemplo de actualización de tarea", description = "Ejemplo completo de cómo actualizar una tarea existente especificando ID, título, descripción, estado y usuario.", value = """
					{
					"title": "Actualizar funcionalidad existente",
					"description": "Modificar la funcionalidad actual para incluir nuevos requisitos según el documento de diseño actualizado.",
					"statusId": 2,
					"userId": 1
					}
					"""))))
	public ResponseEntity<TaskDTO> updateTask(@PathVariable int id, @RequestBody Task taskDetails) {
		TaskDTO updatedTask = taskService.updateTask(id, taskDetails);
		return ResponseEntity.ok(updatedTask);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Elimina una tarea", description = "Elimina una tarea específica por su ID. Lanza una excepción si la tarea no existe.", responses = {
			@ApiResponse(description = "Tarea eliminada", responseCode = "204"),
			@ApiResponse(description = "Tarea no encontrada", responseCode = "404") })
	public ResponseEntity<Void> deleteTask(@PathVariable int id) {
		taskService.deleteTask(id);
		return ResponseEntity.noContent().build();
	}
}
