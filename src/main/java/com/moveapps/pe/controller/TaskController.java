package com.moveapps.pe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moveapps.pe.entities.Task;
import com.moveapps.pe.service.TaskService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/task")
@RequiredArgsConstructor
public class TaskController {

	@Autowired
	private TaskService taskService;

	/**
	 * Get All task 
	 *
	 *  
	 * @return All task.
	 */
	@ApiOperation(value = "All task", notes = "All task", response = Task.class, responseContainer = "Object")
	@GetMapping(value = "")
	public ResponseEntity<List<Task>> getTaskAll() {
		return ResponseEntity.ok(taskService.getTask());
	}

	/**
	 * @param Oject task of type task
	 * @return Object task
	 */
	@ApiOperation(value = "Save task type object", notes = "Save task type object", response = Task.class, responseContainer = "Object")
	@PostMapping("")
	public ResponseEntity<Task> saveTask(@RequestBody Task task) {
		Task saveTask = taskService.save(task);
		return new ResponseEntity<>(saveTask, HttpStatus.CREATED);
	}

	/**
	 * Get a task by its ID.
	 *
	 * @param id The ID of the task to retrieve.
	 * @return The found task.
	 */
	@ApiOperation(value = "Get a task by its ID")
	@GetMapping("/{id}")
	public ResponseEntity<Task> getTaskId(@PathVariable("id") long id) {
		Task task = taskService.getTaskById(id);
		return new ResponseEntity<>(task, HttpStatus.OK);
	}

	/**
	 * Update an existing task.
	 *
	 * @param id   The ID of the task to update.
	 * @param task The updated Task object.
	 * @return The updated task.
	 */
	@ApiOperation(value = "Update an existing task")
	@PutMapping("")
	public ResponseEntity<Task> updateTask(@RequestParam(name = "id") Long id, @RequestBody Task task) {
		Task updateTask = taskService.update(task);
		return new ResponseEntity<>(updateTask, HttpStatus.OK);
	}

	/**
	 * Delete an existing task.
	 *
	 * @param id The ID of the task to delete.
	 */
	@ApiOperation(value = "Delete an existing task")
	@DeleteMapping("")
	public ResponseEntity<Task> deleteTask(@RequestParam(name = "id") long id) {
		Task deleteTask = taskService.delete(id);
		return new ResponseEntity<>(deleteTask, HttpStatus.OK);
	}

}
