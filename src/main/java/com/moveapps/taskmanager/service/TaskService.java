package com.moveapps.taskmanager.service;

import com.moveapps.taskmanager.common.dto.TaskDTO;
import com.moveapps.taskmanager.common.exception.TaskNotFoundException;
import com.moveapps.taskmanager.entity.Task;
import com.moveapps.taskmanager.mappers.TaskMapper;
import com.moveapps.taskmanager.repository.TaskRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;


    public String create(TaskDTO taskDTO){
        var task = taskMapper.toEntity(taskDTO);
        task.setCreatedAt(LocalDateTime.now());
        return taskRepository.save(task)
            .getExternalId();
    }

    public List<Task> findAll(){
        return Streamable.of(taskRepository.findAll())
            .stream()
            .toList();
    }

    public Task findByExternalId(String externalId){
        return Optional.ofNullable(taskRepository.findByExternalIdEquals(externalId))
            .orElseThrow(() -> new TaskNotFoundException(String.format("Task with id [%s] not found", externalId)));
    }

    public void update(TaskDTO taskDTO){
        var taskFound = this.findByExternalId(taskDTO.externalId());
        taskFound.setModifiedAt(LocalDateTime.now());
        taskFound.setName(taskDTO.name());

        taskRepository.save(taskFound);
    }

    public void delete(String externalId){
        var taskFound = this.findByExternalId(externalId);
        taskRepository.delete(taskFound);
    }


}
