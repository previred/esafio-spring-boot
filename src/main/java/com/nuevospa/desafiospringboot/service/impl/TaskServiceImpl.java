package com.nuevospa.desafiospringboot.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuevospa.desafiospringboot.converter.Converter;
import com.nuevospa.desafiospringboot.dto.TaskDTO;
import com.nuevospa.desafiospringboot.exceptions.TaskNotFoundException;
import com.nuevospa.desafiospringboot.exceptions.TaskStatusNotFoundException;
import com.nuevospa.desafiospringboot.exceptions.UserNotFoundException;
import com.nuevospa.desafiospringboot.model.Task;
import com.nuevospa.desafiospringboot.repository.ITaskRepository;
import com.nuevospa.desafiospringboot.repository.ITaskStatusRepository;
import com.nuevospa.desafiospringboot.repository.IUserRepository;
import com.nuevospa.desafiospringboot.service.ITaskService;
import com.nuevospa.desafiospringboot.validations.TaskValidator;

@Service
public class TaskServiceImpl implements ITaskService {
	
    @Autowired
    private ITaskRepository taskRepository;
    
    @Autowired
    private ITaskStatusRepository taskStatusRepository;
    
    @Autowired
    private IUserRepository userRepository;
    
    @Autowired
    private TaskValidator taskValidator;

    @Override
    public TaskDTO saveTask(Task task) {
    	taskValidator.validateTask(task);
    	Task savedTask = taskRepository.save(task);  
        return Converter.getMapper().map(savedTask, TaskDTO.class);
    }

    @Override
    public TaskDTO getTaskById(int id) {
    	Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
    	return Converter.getMapper().map(task, TaskDTO.class);
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        List<Task> listTask = taskRepository.findAll();        
        return listTask.stream().map(entity -> Converter.getMapper().map(entity, TaskDTO.class))
        		.collect(Collectors.toList());
    }

    @Override
    public TaskDTO updateTask(int id, Task taskDetails) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id : " + id));
        
        if (!taskStatusRepository.existsById(taskDetails.getStatusId())) {
            throw new TaskStatusNotFoundException("Task status not found with id: " + taskDetails.getStatusId());
        }
        
        if (!userRepository.existsById(taskDetails.getUserId())) {
            throw new UserNotFoundException("User not found with id: " + taskDetails.getUserId());
        }
        
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setStatusId(taskDetails.getStatusId());
        task.setUserId(taskDetails.getUserId());
        Task savedTask = taskRepository.save(task);
        
        return Converter.getMapper().map(savedTask, TaskDTO.class);
    }

    @Override
    public void deleteTask(int id) {
    	if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException("Task not found with id: " + id);
        }
        taskRepository.deleteById(id);
    }
}
