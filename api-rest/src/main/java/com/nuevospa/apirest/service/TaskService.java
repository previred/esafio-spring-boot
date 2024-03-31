package com.nuevospa.apirest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nuevospa.apirest.model.Task;
import com.nuevospa.apirest.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Component
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	public Task createTask(Task task) {
		return taskRepository.save(task);
	}
	
	public Task getTaskById(Long id) {
		Optional<Task> optionalTask = taskRepository.findById(id);
		return optionalTask.get();
	}
	
	public List<Task> getAllTask(){
		return taskRepository.findAll();
	}
	
	public void deleteTask(Long id) {
		taskRepository.deleteById(id);
	}

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasksByStatusId(Long statusId) {
        return taskRepository.findByStatusId(statusId);
    }
	
}
