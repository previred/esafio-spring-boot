package com.nuevospa.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nuevospa.apirest.model.TaskStatus;
import com.nuevospa.apirest.repository.TaskStatusRepository;

@Component
public class TaskStatusService {
	@Autowired
	private TaskStatusRepository taskStatusRepository;
	
	public List<TaskStatus> getAllTaskStatus(){
		return taskStatusRepository.findAll();
	}
}
