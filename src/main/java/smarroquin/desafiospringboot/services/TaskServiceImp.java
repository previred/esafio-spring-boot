package smarroquin.desafiospringboot.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import smarroquin.desafiospringboot.controllers.ResponseBase;
import smarroquin.desafiospringboot.entities.Task;
import smarroquin.desafiospringboot.entities.TaskStatus;
import smarroquin.desafiospringboot.entities.TaskStatus.Status;
import smarroquin.desafiospringboot.entities.DTO.TaskDTO;
import smarroquin.desafiospringboot.repositories.TaskRepository;
import smarroquin.desafiospringboot.repositories.TaskStatusRepository;

@Service
public class TaskServiceImp implements TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private TaskStatusRepository taskStatusRepository;
	
	@Override
	public List<TaskDTO> getTasks() {
		List<Task> tasks = taskRepository.findAll();
		
		return tasks.stream()
				.map(this::taskToDto)
				.collect(Collectors.toList());
	}
	
	@Override
	public TaskDTO getTask(long id) {
		Optional<Task> optionalTask = taskRepository.findById(id);
		if (optionalTask.isEmpty())
			return null;
		
		return taskToDto(optionalTask.get());
	}
	
	@Override
	public ResponseBase createTask(TaskDTO taskDTO) {
		return saveTask(taskDTO);
	}
	
	@Override
	public ResponseBase updateTask(TaskDTO taskDTO) {
		return saveTask(taskDTO);
	}
	
	@Override
	public ResponseBase deleteTask(long taskId) {
		Optional<Task> optionalTask = taskRepository.findById(taskId);
		if (optionalTask.isEmpty())
			return new ResponseBase(false, "Task ID does not exist");
		
		try {
			taskRepository.delete(optionalTask.get());
		} catch (Exception e) {
			return new ResponseBase(false, "Error when delete task");
		}
		
		return new ResponseBase(true, null);
	}
	
	private TaskDTO taskToDto(Task task) {
		TaskDTO taskDTO = new TaskDTO();
		
		taskDTO.setId(task.getId());
		taskDTO.setName(task.getName());
		taskDTO.setDescription(task.getDescription());
		
		taskDTO.setStatus(task.getTaskStatus().getStatus().toString());
		
		return taskDTO;
	}
	
	private ResponseBase saveTask(TaskDTO taskDTO) {
		Task task = dtoToTask(taskDTO);
		if (Objects.isNull(task))
			return new ResponseBase(false, "Received status is incorrect");
		
		try {
			taskRepository.save(task);
		} catch (Exception e) {
			return new ResponseBase(false, "Error when persist data");
		}
		
		return new ResponseBase(true, null);
	}
	
	private Task dtoToTask(TaskDTO taskDTO) {
		Status status = getTaskStatusEnum(taskDTO.getStatus());
		if (Objects.isNull(status))
			return null;
		
		Optional<TaskStatus> optionalTaskStatus = taskStatusRepository.findByStatus(status);
		if (optionalTaskStatus.isEmpty())
			return null;
		
		Task task = new Task();
		
		task.setId(taskDTO.getId());
		task.setName(taskDTO.getName());
		task.setDescription(taskDTO.getDescription());
		
		task.setTaskStatus(optionalTaskStatus.get());
		
		return task;
	}
	
	private Status getTaskStatusEnum(String taskStatus) {
		switch (taskStatus) {
			case "IN_PROCESS":
				return Status.IN_PROCESS;
			case "COMPLETED":
				return Status.COMPLETED;
			case "REJECTED":
				return Status.REJECTED;
			default:
				return null;
		}
	}
}