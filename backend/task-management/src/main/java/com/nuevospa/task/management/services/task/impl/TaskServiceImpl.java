package com.nuevospa.task.management.services.task.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuevospa.task.management.configuration.GenerateJwtToken;
import com.nuevospa.task.management.data.entity.TaskDao;
import com.nuevospa.task.management.data.entity.TaskStatusDao;
import com.nuevospa.task.management.data.entity.UserDao;
import com.nuevospa.task.management.data.repository.ITaskRepository;
import com.nuevospa.task.management.data.repository.ITaskStatusRepository;
import com.nuevospa.task.management.data.repository.IUserRepository;
import com.nuevospa.task.management.dto.task.Priority;
import com.nuevospa.task.management.dto.task.ResponseTaskDto;
import com.nuevospa.task.management.dto.task.TaskDto;
import com.nuevospa.task.management.exception.TaskException;
import com.nuevospa.task.management.exception.TaskNotFoundException;
import com.nuevospa.task.management.exception.TaskStatusNotFoundException;
import com.nuevospa.task.management.exception.TokenException;
import com.nuevospa.task.management.exception.UserNotFoundException;
import com.nuevospa.task.management.services.task.ITaskService;
import com.nuevospa.task.management.util.CodesErrorsConstants;
import com.nuevospa.task.management.util.MessageErrorsConstants;
import com.nuevospa.task.management.validator.ValidPriority;


@Service
public class TaskServiceImpl implements ITaskService{
	

    
    @Autowired
    private IUserRepository repositoryUser;
    
    @Autowired
    private ITaskRepository repositoryTask;
    
    @Autowired
    private ITaskStatusRepository repositoryStatusTask;

	@Override
	public ResponseTaskDto createTask(TaskDto reqCreateTask, String token) {
		
		ResponseTaskDto response = new ResponseTaskDto();
		
		Priority priority = ValidPriority.validPriority(reqCreateTask.getPriority());

		validateToken(token);
		
		
		Optional<UserDao> validUser = repositoryUser.findByEmail(reqCreateTask.getEmailAsignado());
		if(!validUser.isPresent()) {
			throw new UserNotFoundException(MessageErrorsConstants.USER_NOT_FOUND_TASK_MESSAGE, CodesErrorsConstants.USER_NOT_FOUND_TASK_CODE);
		}
		
		
		Optional<TaskStatusDao> status = repositoryStatusTask.findByStatus("IN PROGRESS");
		
		if(!status.isPresent()) {
			throw new TaskStatusNotFoundException(MessageErrorsConstants.NOT_FOUND_STATUS_MESSAGE, 
					CodesErrorsConstants.NOT_FOUND_STATUS_CODE);
		}
		

		try {

			TaskDao saveTask = repositoryTask.save(mapperTaskDao(reqCreateTask, priority, validUser.get(), status.get()));
			response = mapperResponse(saveTask);

		} catch (Exception e) {
			throw new TaskException(MessageErrorsConstants.TASK_CREATE_ERROR_EXCEPTION_MESSAGE + " " + e.getMessage(),
					CodesErrorsConstants.TASK_CREATE_ERROR_EXCEPTION_CODE);
		}

		return response;
	}
	
	
	
	
	private TaskDao mapperTaskDao(TaskDto createTask, Priority priority, UserDao userDao, TaskStatusDao status) {
		
		
		TaskDao taskDao = new TaskDao();
		
		taskDao.setTitle(createTask.getTitle());
		taskDao.setDescription(createTask.getDescription());
		taskDao.setStartDate(LocalDateTime.now());
		taskDao.setPriority(priority);
		taskDao.setUser(userDao);
		taskDao.setStatus(status);
		
		
		return taskDao;
		
	}
	
	
	private ResponseTaskDto mapperResponse(TaskDao savetask) {
		
		ResponseTaskDto response = ResponseTaskDto.builder()
				.priority(savetask.getPriority())
				.title(savetask.getTitle())
				.description(savetask.getDescription())
				.startDate(savetask.getStartDate())
				.finishDate(savetask.getFinishDate())
				.status(savetask.getStatus().getStatus())
				.assigned(savetask.getUser().getName())
				.idTask(savetask.getId())
				.build();
		
		
		return response;		
		
	}


	@Override
	public ResponseTaskDto editTask(TaskDto reqEditTask, String token, Long idTask) {
		
		ResponseTaskDto resp = new ResponseTaskDto(); 
		
		validateToken(token);
		
		Optional<TaskDao> taskDao = repositoryTask.findById(idTask);
		
		if(taskDao.isEmpty()) {
			throw new TaskNotFoundException(MessageErrorsConstants.NOT_FOUND_TASK_EDIT_MESSAGE, 
					CodesErrorsConstants.NOT_FOUND_TASK_EDIT_CODE);
		}
		
		Optional<TaskStatusDao> status = repositoryStatusTask.findByStatus(reqEditTask.getStatus());
		
		if(!status.isPresent()) {
			throw new TaskStatusNotFoundException(MessageErrorsConstants.NOT_FOUND_STATUS_MESSAGE, 
					CodesErrorsConstants.NOT_FOUND_STATUS_CODE);
		}
		
		Optional<UserDao> validUser = repositoryUser.findByEmail(reqEditTask.getEmailAsignado());
		if(!validUser.isPresent()) {
			throw new UserNotFoundException(MessageErrorsConstants.USER_NOT_FOUND_TASK_MESSAGE, CodesErrorsConstants.USER_NOT_FOUND_TASK_CODE);
		}
			
		
		try {
			
			TaskDao editTask = taskDao.get();
			
			editTask.setTitle(reqEditTask.getTitle());
			editTask.setDescription(reqEditTask.getDescription());
			editTask.setPriority(ValidPriority.validPriority(reqEditTask.getPriority()));
			editTask.setStatus(status.get());
			editTask.setUser(validUser.get());
			
			editTask.setFinishDate(status.get().getStatus().equals("DONE")? LocalDateTime.now() : null);	
				
			TaskDao saveTask = repositoryTask.save(editTask);
			resp = mapperResponse(saveTask);

		} catch (Exception e) {
			throw new TaskException(MessageErrorsConstants.TASK_EDIT_ERROR_EXCEPTION_MESSAGE + " " + e.getMessage(),
					CodesErrorsConstants.TASK_EDIT_ERROR_EXCEPTION_CODE);
		}
		
		return resp;
	}




	@Override
	public void deleteTask(String token, Long idTask) {
		Optional<TaskDao> taskDao = repositoryTask.findById(idTask);
		
		validateToken(token);
		
		if(taskDao.isEmpty()) {
			throw new TaskNotFoundException(MessageErrorsConstants.NOT_FOUND_TASK_DELETE_MESSAGE, 
					CodesErrorsConstants.NOT_FOUND_TASK_DELETE_CODE);
		}
		
		try {
				
			repositoryTask.deleteById(idTask);
			

		} catch (Exception e) {
			throw new TaskException(MessageErrorsConstants.TASK_DELETE_ERROR_EXCEPTION_MESSAGE + " " + e.getMessage(),
					CodesErrorsConstants.TASK_DELETE_ERROR_EXCEPTION_CODE);
		}
		
	}


	@Override
	public List<ResponseTaskDto> getTaskAll(String token) {
		
		List<ResponseTaskDto> listTasks = new ArrayList<>();
		
		List<TaskDao> taskListDao = repositoryTask.findAll();
		
		validateToken(token);
		
		if(taskListDao.isEmpty()) {
			throw new TaskNotFoundException(MessageErrorsConstants.NOT_FOUND_TASKS_MESSAGE, 
					CodesErrorsConstants.NOT_FOUND_TASKS_CODE);
		}
		
		taskListDao.forEach(task -> {
			
			listTasks.add(mapperResponse(task));
			
		});
		
		return listTasks;
	}
	
	private void validateToken(String token) {
		
		String emailToken = GenerateJwtToken.getEmailToken(token);

		if (!repositoryUser.existsByEmail(emailToken) || !GenerateJwtToken.validateToken(token, emailToken)) {
			throw new TokenException(MessageErrorsConstants.TOKEN_EXCEPTION_MESSAGE, CodesErrorsConstants.TOKEN_EXCEPTION_MESSAGE_CODE);
		}
		
	}
	

	
}
