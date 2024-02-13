package com.moveapps.management.statustask.applications.rest;

import static com.moveapps.management.user.infraestructure.confing.Constants.REQUEST_MAPPING_STATUSTASKS;

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

import com.moveapps.management.statustask.domains.data.StatusTaskDTO;
import com.moveapps.management.statustask.domains.services.StatusTaskService;
import com.moveapps.management.statustask.infraestructure.adapters.StatusTaskAdapter;
import com.moveapps.management.task.domains.data.TaskDTO;
import com.moveapps.management.task.domains.services.TaskService;
import com.moveapps.management.task.infraestructure.adapters.TaskAdapter;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(REQUEST_MAPPING_STATUSTASKS)
public class StatusTaskController {
	@Autowired
	StatusTaskService statusTaskService;
	
	@Autowired
	StatusTaskAdapter statusTaskdapter;
	
	@ApiOperation("Search All StatusTasks")
	@GetMapping
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok()
							.body(statusTaskdapter.toResponseBase(statusTaskService.getAll()));
	}
	@ApiOperation("Search StatusTask by id")
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable String id) {
		return ResponseEntity.ok()
							.body(statusTaskdapter.toResponseBase(statusTaskService.getById(id)));
	}
	
	@ApiOperation("Save StatusTask")
	@PostMapping
	public ResponseEntity<?> save(@RequestBody StatusTaskDTO statusTaskdDTO) {
		return ResponseEntity.ok()
							.body(statusTaskdapter.toResponseBase(statusTaskService.save(statusTaskdapter.toEntity(statusTaskdDTO))));
	}
	
	@ApiOperation("Update StatusTask")
	@PutMapping
	public ResponseEntity<?> update(@RequestBody StatusTaskDTO statusTaskdDTO) {
		return ResponseEntity.ok()
						.body(statusTaskdapter.toResponseBase(statusTaskService.update(statusTaskdapter.toEntity(statusTaskdDTO))));

	}
	@ApiOperation("Delete Task")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		return ResponseEntity.ok()
				.body(statusTaskdapter.toResponseBase(statusTaskService.delete(id)));

	}
}
