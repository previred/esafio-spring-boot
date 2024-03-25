package smarroquin.desafiospringboot.controllers;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import smarroquin.desafiospringboot.entities.DTO.TaskDTO;
import smarroquin.desafiospringboot.services.TaskService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/task")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@GetMapping
	public ResponseEntity<List<TaskDTO>> getTasks() {
		return ResponseEntity.ok(taskService.getTasks());
	}
	
	@GetMapping(value = "/{taskId}")
	public ResponseEntity<TaskDTO> getTask(@PathVariable long taskId) {
		TaskDTO taskDto = taskService.getTask(taskId);
		
		if (Objects.isNull(taskDto))
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(taskDto);
	}
	
	@PostMapping
	public ResponseEntity<ResponseBase> createTask(@RequestBody TaskDTO taskDTO) {
		return ResponseEntity.ok(taskService.createTask(taskDTO));
	}
	
	@PutMapping
	public ResponseEntity<ResponseBase> updateTask(@RequestBody TaskDTO taskDTO) {
		return ResponseEntity.ok(taskService.updateTask(taskDTO));
	}
	
	@DeleteMapping(value = "/{taskId}")
	public ResponseEntity<ResponseBase> deleteTask(@PathVariable long taskId) {
		return ResponseEntity.ok(taskService.deleteTask(taskId));
	}
}