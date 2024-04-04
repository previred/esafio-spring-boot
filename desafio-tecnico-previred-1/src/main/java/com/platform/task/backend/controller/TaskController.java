package com.platform.task.backend.controller;

import com.platform.task.backend.entity.Task;
import com.platform.task.backend.service.TaskService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import jakarta.validation.Valid;

@RestController
public class TaskController {

	@Autowired
	TaskService taskService;

	private static final Log LOGGER = LogFactory.getLog("TaskController" + TaskController.class);

	@ApiOperation(value = "Listar tareas", notes = "Muestra lista de las tareas existentes", response = Task.class, responseContainer = "List")
	@GetMapping("/findAllTasks")
	public List<Task> findAllTasks() {
		LOGGER.info("*****INICIO MOTRAR TAREAS EXISTENTES*****");
		return taskService.findAllTasks();
	}

	@ApiOperation(value = "Guarda tarea", notes = "Guarda tarea", response = Task.class, responseContainer = "Object")
	@PostMapping("/saveTask")
	public Task saveTask(@Valid @RequestBody Task task) {
		LOGGER.info("*****INICIO CREAR NUEVA TAREA*****");
		return taskService.saveTask(task);
	}

	@ApiOperation(value = "Modifica tarea", notes = "Modifica tarea", response = Task.class, responseContainer = "Object")
	@PutMapping("/updateTask/{id}")
	public Task updateTask(@RequestBody Task task) {
		LOGGER.info("*****INICIO PMODIFICAR TAREA POR ID*****");
		return taskService.updateTask(task);
	}

	@ApiOperation(value = "Elimina una tarea", notes = "Elimina tarea por id")
	@DeleteMapping("/deleteTask/{id}")
	public String deleteTask(@PathVariable Long id) {
		LOGGER.info("*****INICIO ELIMINAR TAREA POR ID*****");
		taskService.deleteTask(id);
		return "Succesfully deleted";

	}

	@ApiOperation(value = "Buscar una tarea", notes = "Buscar una tarea de acuerdo a un identificador", response = Task.class, responseContainer = "Object")
	@GetMapping("/findById/{id}")
	public Task findById(@PathVariable("id") Long id) {
		LOGGER.info("*****INICIO BUSCAR TAREA POR ID*****");
		return taskService.findById(id);
	}
	
	
	
}
