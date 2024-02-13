package com.moveapps.management.task.applications.rest;

import static com.moveapps.management.user.infraestructure.confing.Constants.REQUEST_MAPPING_TASKS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moveapps.management.task.domains.data.TaskDTO;
import com.moveapps.management.task.domains.services.TaskService;
import com.moveapps.management.task.infraestructure.adapters.TaskAdapter;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(REQUEST_MAPPING_TASKS)
public class TaskController {
	@Autowired
	TaskService taskService;
	
	@Autowired
	TaskAdapter taskdapter;
	
	@ApiOperation("Search All Tasks")
	@GetMapping
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok()
							.body(taskdapter.toResponseBase(taskService.getAll()));
	}
	@ApiOperation("Search Task by id")
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable String id) {
		return ResponseEntity.ok()
							.body(taskdapter.toResponseBase(taskService.getById(id)));
	}
	
	@ApiOperation("Save Task")
	@PostMapping
	public ResponseEntity<?> save(@RequestBody TaskDTO taskDTO) {
		return ResponseEntity.ok()
							.body(taskdapter.toResponseBase(taskService.save(taskdapter.toEntity(taskDTO))));
	}
	
	@ApiOperation("Update Task")
	@PutMapping
	public ResponseEntity<?> update(@RequestBody TaskDTO taskDTO) {
		return ResponseEntity.ok()
						.body(taskdapter.toResponseBase(taskService.update(taskdapter.toEntity(taskDTO))));

	}
	@ApiOperation("Delete Task")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		return ResponseEntity.ok()
				.body(taskdapter.toResponseBase(taskService.delete(id)));

	}
}
