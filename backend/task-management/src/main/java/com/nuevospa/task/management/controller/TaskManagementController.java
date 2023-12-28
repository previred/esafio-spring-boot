package com.nuevospa.task.management.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.nuevospa.task.management.dto.response.ResponseService;
import com.nuevospa.task.management.dto.task.ResponseTaskDto;
import com.nuevospa.task.management.dto.task.TaskDto;
import com.nuevospa.task.management.services.task.ITaskService;
import com.nuevospa.task.management.util.CodeResponse;
import com.nuevospa.task.management.util.MessageResponse;

@RestController
public class TaskManagementController {
	
	@Autowired
    private ITaskService service;
	
	@PostMapping("/create-task")
	public ResponseEntity<ResponseService<ResponseTaskDto>> createUser(@Valid @RequestBody TaskDto createTask, @RequestHeader("token") String token){
		
		ResponseTaskDto response = service.createTask(createTask, token);
		
		ResponseService<ResponseTaskDto> resp = 
				new ResponseService<>(CodeResponse.SUCCESS_CODE, MessageResponse.TASK_CREATE_SUCCESS_MESSAGE, response);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
		
	}
	
	
	@PutMapping("/edit-task/{id}")
	public ResponseEntity<ResponseService<ResponseTaskDto>> editUser(@Valid @RequestBody TaskDto createTask, @RequestHeader("token") String token,  @PathVariable(value = "id") Long id){
		
		ResponseTaskDto response = service.editTask(createTask, token, id);
		
		ResponseService<ResponseTaskDto> resp = 
				new ResponseService<>(CodeResponse.SUCCESS_CODE, MessageResponse.TASK_EDIT_SUCCESS_MESSAGE, response);
		
		return ResponseEntity.status(HttpStatus.OK).body(resp);
		
	}
	
	
	@DeleteMapping("/delete-task/{id}")
	public ResponseEntity<ResponseService<ResponseTaskDto>> deleteUser(@RequestHeader("token") String token,  @PathVariable(value = "id") Long id){
		
		service.deleteTask(token, id);
		
		ResponseService<ResponseTaskDto> resp = 
				new ResponseService<>(CodeResponse.SUCCESS_CODE, MessageResponse.TASK_DELETE_SUCCESS_MESSAGE);
		
		return ResponseEntity.status(HttpStatus.OK).body(resp);
		
	}
	
	@GetMapping("/tasks")
    public ResponseEntity<ResponseService<List<ResponseTaskDto>>> getTaskAll(@RequestHeader("token") String token) {
		
	
		List<ResponseTaskDto> response = service.getTaskAll(token);
		
		ResponseService<List<ResponseTaskDto>> resp = 
				new ResponseService<>(CodeResponse.SUCCESS_CODE, MessageResponse.GET_TASKS_SUCCESS_MESSAGE, response);

		return ResponseEntity.status(HttpStatus.OK).body(resp);

    }

}
