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
import com.moveapps.pe.entities.TaskState;
import com.moveapps.pe.service.TaskService;
import com.moveapps.pe.service.TaskStateService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/taskState")
@RequiredArgsConstructor
public class TaskStateController {

	@Autowired
	private TaskStateService taskStateService;

	/**
	 * Get All task 
	 *
	 *  
	 * @return All task.
	 */
	@ApiOperation(value = "All task", notes = "All task", response = Task.class, responseContainer = "Object")
	@GetMapping(value = "")
	public ResponseEntity<List<TaskState>> getTaskStateAll() {
		return ResponseEntity.ok(taskStateService.getTaskState());
	}

	/**
	 * @param Oject task of type task
	 * @retfurn Object task
	 */
	@ApiOperation(value = "Save task type object", notes = "Save task type object", response = Task.class, responseContainer = "Object")
	@PostMapping("")
	public ResponseEntity<TaskState> saveTaskState(@RequestBody TaskState task) {
		TaskState saveTask = taskStateService.save(task);
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
	public ResponseEntity<TaskState> getTaskStateId(@PathVariable("id") long id) {
		TaskState task = taskStateService.getTaskStateById(id);
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
	public ResponseEntity<TaskState> updateTaskState(@RequestParam(name = "id") Long id, @RequestBody TaskState task) {
		TaskState updateTask = taskStateService.update(task);
		return new ResponseEntity<>(updateTask, HttpStatus.OK);
	}

	/**
	 * Delete an existing task.
	 *
	 * @param id The ID of the task to delete.
	 */
	@ApiOperation(value = "Delete an existing task")
	@DeleteMapping("")
	public ResponseEntity<TaskState> deleteTaskState(@RequestParam(name = "id") long id) {
		TaskState deleteTask = taskStateService.delete(id);
		return new ResponseEntity<>(deleteTask, HttpStatus.OK);
	}

}
