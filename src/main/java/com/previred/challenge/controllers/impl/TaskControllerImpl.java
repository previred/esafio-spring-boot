package com.previred.challenge.controllers.impl;

import com.previred.challenge.controllers.BaseController;
import com.previred.challenge.controllers.TaskController;
import com.previred.challenge.dto.JsonMessageDTO;
import com.previred.challenge.dto.PagedTaskResponseDTO;
import com.previred.challenge.dto.TaskRequestDTO;
import com.previred.challenge.dto.TaskResponseDTO;
import com.previred.challenge.services.TaskService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/tasks")
public class TaskControllerImpl extends BaseController implements TaskController {

    @NonNull
    private final TaskService taskService;

    @Override
    public TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO) {
        var loggedUserId = getLoggedUser().id();
        log.debug("Saving task {} for user {}", taskRequestDTO, loggedUserId);
        var savedTask = taskService.add(loggedUserId, taskRequestDTO);
        log.info("Saved task id {} for user {}", savedTask.id(), loggedUserId);
        return savedTask;
    }

    @Override
    public List<TaskResponseDTO> createTaskBulk(List<TaskRequestDTO> taskRequestDTOList) {
        var loggedUserId = getLoggedUser().id();
        log.info("Saving {} task for user {}", taskRequestDTOList.size(), loggedUserId);
        return taskService.addAll(loggedUserId, taskRequestDTOList);
    }

    @Override
    public TaskResponseDTO getTaskById(Integer taskId) {
        var loggedUser = getLoggedUser();
        log.debug("Retrieving task {} for user {}", taskId, loggedUser.id());
        return taskService.get(loggedUser.id(), taskId);
    }

    @Override
    public TaskResponseDTO updateTask(Integer taskId, TaskRequestDTO taskRequestDTO) {
        var loggedUserId = getLoggedUser().id();
        log.debug("Updating task {} for user {}", taskId, loggedUserId);
        return taskService.updateTask(loggedUserId, taskId, taskRequestDTO);
    }

    @Override
    public JsonMessageDTO removeTask(Integer taskId) {
        var loggedUserId = getLoggedUser().id();
        log.info("Removing task {} for user {}", taskId, loggedUserId);
        taskService.deleteTask(loggedUserId, taskId);
        return JsonMessageDTO.ok();
    }

    @Override
    public PagedTaskResponseDTO list(Integer page, Integer sizeInteger) {
        var loggedUserId = getLoggedUser().id();
        log.info("List tasks for {} page {}", loggedUserId, page);
        return taskService.list(loggedUserId, PageRequest.of(page, sizeInteger));
    }

}
