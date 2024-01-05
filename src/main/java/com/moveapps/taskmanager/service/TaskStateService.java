package com.moveapps.taskmanager.service;

import com.moveapps.taskmanager.common.dto.TaskStateDTO;
import com.moveapps.taskmanager.common.exception.TaskNotFoundException;
import com.moveapps.taskmanager.entity.TaskState;
import com.moveapps.taskmanager.entity.TaskState.TaskStatus;
import com.moveapps.taskmanager.mappers.TaskStateMapper;
import com.moveapps.taskmanager.repository.TaskStateRepository;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class TaskStateService {

    private final TaskStateRepository taskStateRepository;
    private final TaskStateMapper taskStateMapper;


    public String create(TaskStateDTO taskStateDTO){
        var taskState = taskStateMapper.toEntity(taskStateDTO);
        taskState.setCreatedAt(LocalDateTime.now());
        if(Objects.isNull(taskState.getTaskStatus())){
            taskState.setTaskStatus(TaskStatus.CREATED);
        }

        return taskStateRepository.save(taskState)
            .getExternalId();
    }

    public TaskState findByExternalId(String externalId){
        return Optional.ofNullable(taskStateRepository.findByExternalIdEquals(externalId))
            .orElseThrow(() -> new TaskNotFoundException(String.format("TaskState with id [%s] not found", externalId)));
    }

    public void update(TaskStateDTO taskStateDTO){
        var taskStateFound = this.findByExternalId(taskStateDTO.externalId());

        taskStateFound.setTaskId(taskStateDTO.taskId());
        taskStateFound.setTaskStatus(taskStateDTO.taskStatus());
        taskStateFound.setModifiedAt(LocalDateTime.now());

        taskStateRepository.save(taskStateFound);
    }

    public void delete(String externalId){
        var taskStateFound = this.findByExternalId(externalId);
        taskStateRepository.delete(taskStateFound);
    }


}
