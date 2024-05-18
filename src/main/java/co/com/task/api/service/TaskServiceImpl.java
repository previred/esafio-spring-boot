package co.com.task.api.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;

import javax.validation.Validator;

import org.springframework.stereotype.Service;

import co.com.task.api.domain.Task;
import co.com.task.api.dto.TaskDTO;
import co.com.task.api.exceptions.ExceptionManager;
import co.com.task.api.mapper.TaskMapper;
import co.com.task.api.repository.TaskRepository;
import co.com.task.api.repository.UserRepository;
import co.com.task.api.utility.Utilities;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final Validator validator;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository, Validator validator) {
	this.taskRepository = taskRepository;
	this.userRepository = userRepository;
	this.validator = validator;
    }

    @Override
    public TaskDTO getById(UUID idTask) {
	return TaskMapper.taskDomainToTaskDto(
		taskRepository.findById(idTask)
			.orElseThrow(() -> new ExceptionManager().new NotFoundException("Task")));
    }

    @Override
    public List<TaskDTO> getAll() {
	return StreamSupport.stream(taskRepository.findAll().spliterator(), false)
		.map(TaskMapper::taskDomainToTaskDto).toList();
    }

    @Override
    public TaskDTO save(TaskDTO taskDto) {
	Task task = TaskMapper.taskDtoToTaskDomain(taskDto);
	Utilities.validate(task, validator, "Task");

	if (Optional.ofNullable(taskDto.getIdTask()).isPresent()
		&& taskRepository.findById(taskDto.getIdTask()).isPresent()) {
	    throw new ExceptionManager().new SaveException(
		    "Ya existe la tarea ingresada: ".concat(taskDto.getIdTask().toString()));
	}
	return TaskMapper.taskDomainToTaskDto(taskRepository.save(TaskMapper.taskDtoToTaskDomain(taskDto)));
    }

    @Override
    public TaskDTO update(TaskDTO taskDto) {
	Task task = taskRepository.findById(taskDto.getIdTask())
		.orElseThrow(() -> new ExceptionManager().new UpdateException(taskDto.getIdTask().toString()));

	UUID idUser = Optional.ofNullable(taskDto.getIdUser())
		.orElseThrow(() -> new ExceptionManager().new EmptyFieldException("El idUser no puede estar vacio"));

	userRepository.findById(idUser)
		.orElseThrow(() -> new ExceptionManager().new UpdateException(taskDto.getIdUser().toString()));

	task = TaskMapper.updateTaskDtoToTaskDomain(task, taskDto);
	Utilities.validate(task, validator, "Task");
	return TaskMapper.taskDomainToTaskDto(taskRepository.save(task));
    }

    @Override
    public void delete(UUID idTask) {
	Task task = taskRepository.findById(idTask)
		.orElseThrow(() -> new ExceptionManager().new NotFoundException(idTask.toString()));
	taskRepository.delete(task);
    }

}
