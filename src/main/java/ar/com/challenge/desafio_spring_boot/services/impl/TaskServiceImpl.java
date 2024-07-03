package ar.com.challenge.desafio_spring_boot.services.impl;

import ar.com.challenge.desafio_spring_boot.dto.TaskDto;
import ar.com.challenge.desafio_spring_boot.entity.Task;
import ar.com.challenge.desafio_spring_boot.entity.TaskState;
import ar.com.challenge.desafio_spring_boot.exception.ResourceFoundException;
import ar.com.challenge.desafio_spring_boot.exception.ResourceNotFoundException;
import ar.com.challenge.desafio_spring_boot.mapper.TaskMapper;
import ar.com.challenge.desafio_spring_boot.repository.TaskRepository;
import ar.com.challenge.desafio_spring_boot.repository.TaskStateRepository;
import ar.com.challenge.desafio_spring_boot.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskStateRepository taskStateRepository;
    private final TaskRepository taskRepository;

    @Override
    public List<TaskDto> getAll() {
        return taskRepository.findAll().stream().map(TaskMapper::toDto).toList();
    }

    @Override
    public TaskDto save(TaskDto taskDto) throws ResourceFoundException, ResourceNotFoundException {

        TaskState existTaskState = taskStateRepository
                .findByStatus(taskDto.getStatus().getStatus())
                .orElseThrow(() -> new ResourceNotFoundException("State not Found"));

        Optional<Task> task = taskRepository.findByName(taskDto.getName());

        if (task.isPresent()) {
            throw new ResourceFoundException("Task with name " + taskDto.getName() + " already exists");
        }

        var taskNew = TaskMapper.toEntity(taskDto);

        taskNew.setStatus(existTaskState);

        return TaskMapper.toDto(taskRepository.save(taskNew));
    }

    @Override
    public TaskDto getById(Integer id) throws ResourceNotFoundException {

        var task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not Found"));

        return TaskMapper.toDto(task);
    }

    @Override
    public TaskDto delete(Integer id) throws ResourceNotFoundException {

        var task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not Found"));

        taskRepository.deleteById(id);

        return TaskMapper.toDto(task);
    }

    @Override
    public TaskDto update(TaskDto taskDto) throws ResourceNotFoundException {
        Task existTask = taskRepository
                .findById(taskDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Task not Found"));

        TaskState existTaskState = taskStateRepository
                .findByStatus(taskDto.getStatus().getStatus())
                .orElseThrow(() -> new ResourceNotFoundException("State not Found"));

        existTask.setName(taskDto.getName());
        existTask.setStatus(existTaskState);

        return TaskMapper.toDto(taskRepository.save(existTask));
    }
}
