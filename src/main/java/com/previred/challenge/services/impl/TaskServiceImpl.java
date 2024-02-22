package com.previred.challenge.services.impl;

import com.previred.challenge.dto.PagedTaskResponseDTO;
import com.previred.challenge.dto.TaskRequestDTO;
import com.previred.challenge.dto.TaskResponseDTO;
import com.previred.challenge.exceptions.TaskNoAccessException;
import com.previred.challenge.exceptions.TaskNotFoundException;
import com.previred.challenge.mappers.TaskModelMapper;
import com.previred.challenge.model.TaskModel;
import com.previred.challenge.repositories.TaskRepository;
import com.previred.challenge.services.TaskService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    @NonNull
    private final TaskRepository taskRepository;

    @NonNull
    private final TaskModelMapper taskModelMapper;

    public TaskResponseDTO get(Integer userId, Integer taskId) {
        return taskRepository.findByIdAndUserId(taskId, userId)
                .map(taskModelMapper::toResponseDTO)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
    }

    public TaskResponseDTO add(Integer userId, TaskRequestDTO taskRequestDTO) {
        var taskModel = taskModelMapper.toModel(taskRequestDTO);
        return taskModelMapper.toResponseDTO(taskRepository.save(taskModel));
    }

    public List<TaskResponseDTO> addAll(Integer userId, List<TaskRequestDTO> taskRequestDTOList) {
        var taskModelList = taskModelMapper.toModelList(taskRequestDTOList);
        var savedTaskModelList = new LinkedList<TaskResponseDTO>();
        taskRepository.saveAll(taskModelList)
                .forEach(e -> savedTaskModelList.add(taskModelMapper.toResponseDTO(e)));
        return savedTaskModelList;
    }

    @Override
    public TaskResponseDTO updateTask(Integer userId, Integer taskId, TaskRequestDTO taskRequestDTO) {
        var entity = checkPermissions(userId, taskId);
        entity.setName(taskRequestDTO.name());
        entity.setDescription(taskRequestDTO.description());
        entity.setStatus(taskRequestDTO.status());
        return taskModelMapper.toResponseDTO(taskRepository.save(entity));
    }

    public void deleteTask(Integer userId, Integer taskId) {
        var entity = checkPermissions(userId, taskId);
        taskRepository.delete(entity);
    }

    public PagedTaskResponseDTO list(Integer userId, Pageable pageRequest) {
        var page = taskRepository.findByUserId(userId, pageRequest);
        var content = page.getContent().stream()
                .map(taskModelMapper::toResponseDTO)
                .toList();
        return new PagedTaskResponseDTO(content, page.getTotalPages(), page.getTotalElements());
    }

    public TaskModel checkPermissions(Integer userId, Integer taskId) {
        var entity = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
        if (!entity.getUserId().equals(userId)) {
            throw new TaskNoAccessException(userId, taskId);
        }
        return entity;
    }

}
