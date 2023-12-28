package com.nuevospa.task.management.services.task;

import com.nuevospa.task.management.dto.task.TaskDto;

import java.util.List;

import com.nuevospa.task.management.dto.task.ResponseTaskDto;

public interface ITaskService {
	
	ResponseTaskDto createTask(TaskDto reqCreateTask, String token);
	
	ResponseTaskDto editTask(TaskDto reqEditTask, String token, Long idTask);
	
	void deleteTask(String token, Long idTask);
	
	List<ResponseTaskDto> getTaskAll(String token);
	

}
