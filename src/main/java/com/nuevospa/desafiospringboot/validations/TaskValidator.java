package com.nuevospa.desafiospringboot.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nuevospa.desafiospringboot.exceptions.BadRequestException;
import com.nuevospa.desafiospringboot.exceptions.TaskStatusNotFoundException;
import com.nuevospa.desafiospringboot.exceptions.UserNotFoundException;
import com.nuevospa.desafiospringboot.model.Task;
import com.nuevospa.desafiospringboot.repository.ITaskStatusRepository;
import com.nuevospa.desafiospringboot.repository.IUserRepository;

@Component
public class TaskValidator {

    private final ITaskStatusRepository taskStatusRepository;
    private final IUserRepository userRepository;

    @Autowired
    public TaskValidator(ITaskStatusRepository taskStatusRepository, IUserRepository userRepository) {
        this.taskStatusRepository = taskStatusRepository;
        this.userRepository = userRepository;
    }

    public void validateTask(Task task) throws BadRequestException {
    	
    	StringBuilder message = new StringBuilder();

        if (task.getTitle() == null || task.getTitle().trim().isEmpty()) {
            message.append("Title must not be null or empty.");
        }
        
        if (task.getDescription() == null) {
            if (message.length() > 0) {
                message.append(" And ");
            }
            message.append("Description must not be null or empty.");
        }

        if (task.getStatusId() == null) {
            if (message.length() > 0) {
                message.append(" And ");
            }
            message.append("Status ID must not be null.");
        }

        if (task.getUserId() == null) {
            if (message.length() > 0) {
                message.append(" And ");
            }
            message.append("User ID must not be null.");
        }

        if (message.length() > 0) {
            throw new BadRequestException(message.toString());
        }
    	
    	if (!taskStatusRepository.existsById(task.getStatusId())) {
            throw new TaskStatusNotFoundException("Task status not found with id: " + task.getStatusId());
        }
        if (!userRepository.existsById(task.getUserId())) {
            throw new UserNotFoundException("User not found with id: " + task.getUserId());
        }
        
    }
}