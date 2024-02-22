package com.previred.challenge.services;

import com.previred.challenge.dto.PagedTaskResponseDTO;
import com.previred.challenge.dto.TaskRequestDTO;
import com.previred.challenge.dto.TaskResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {

    TaskResponseDTO get(Integer userId, Integer taskId);

    TaskResponseDTO add(Integer userId, TaskRequestDTO taskRequestDTO);

    List<TaskResponseDTO> addAll(Integer userId, List<TaskRequestDTO> taskRequestDTOList);

    TaskResponseDTO updateTask(Integer userId, Integer taskId, TaskRequestDTO taskRequestDTO);

    void deleteTask(Integer userId, Integer taskId);

    PagedTaskResponseDTO list(Integer userId, Pageable pageRequest);

}
