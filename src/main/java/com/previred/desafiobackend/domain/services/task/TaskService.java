package com.previred.desafiobackend.domain.services.task;

import com.previred.desafiobackend.data.entities.Task;
import com.previred.desafiobackend.data.entities.TaskStatus;
import com.previred.desafiobackend.data.entities.User;
import com.previred.desafiobackend.data.mapper.TaskMapper;
import com.previred.desafiobackend.data.repository.task.TaskRepository;
import com.previred.desafiobackend.domain.dto.enums.TaskStatusEnum;
import com.previred.desafiobackend.domain.dto.task.request.CreateTask;
import com.previred.desafiobackend.domain.dto.task.response.GetTask;
import com.previred.desafiobackend.domain.exception.task.NonValidStatusChangeException;
import com.previred.desafiobackend.domain.exception.task.TaskNotFoundException;
import com.previred.desafiobackend.domain.services.task.status.StatusService;
import com.previred.desafiobackend.domain.services.user.UserService;
import lombok.extern.log4j.Log4j2;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Miguel Angel
 * @since v1.0.0
 */

@Service
@Log4j2
public class TaskService {

    private TaskRepository taskRepository;
    private StatusService statusService;
    private UserService userService;

    public TaskService(TaskRepository taskRepository, StatusService statusService, UserService userService) {
        this.taskRepository = taskRepository;
        this.statusService = statusService;
        this.userService = userService;
    }

    public GetTask getTask(Long taskId) {
        log.info("[getTask] Searching task with id [{}]", taskId);
        Optional<Task> searchedTask = taskRepository.findById(taskId);
        return TaskMapper.taskDtoFromEntity(searchedTask.orElseThrow(TaskNotFoundException::thrown));
    }

    public List<GetTask> getAllTasks() {
        List<GetTask> taskList = new ArrayList<>();
        log.info("[getAllTasks] Searching All tasks");
        taskRepository.findAll().forEach(taskEntity -> {
            taskList.add(TaskMapper.taskDtoFromEntity(taskEntity));
        });
        log.info("[getAllTasks] Amount of task found [{}]", taskList.size());
        return taskList;
    }

    public void create(CreateTask createTask) {
        log.info("[create] Creating new task with request [{}]", createTask);
        try {
            User user = userService.findByEmail(createTask.getAsignedUserEmail());
            Task createdTask = taskRepository.save(TaskMapper.fromRequest(createTask, statusService.getStatus("PENDING"), user));
            log.info("[create] New Task created with id [{}]", createdTask.getId());
        } catch (Exception ex) {
            log.error("[create] Cannot create user, cause [{}]", ex.getMessage());
            throw ex;
        }
    }

    public void delete(Long taskId) {
        log.info("[delete] Perform delete for task with id [{}]", taskId);
        try {
            Optional<Task> taskToBeDeleted = taskRepository.findById(taskId);
            taskToBeDeleted.ifPresentOrElse(taskRepository::delete, TaskNotFoundException::thrown);
            log.info("[delete] Task deleted");
        } catch (Exception ex) {
            log.error("[delete] Cannot delete task, cause: [{}]", ex.getMessage());
            throw ex;
        }
    }

    public void updateTaskStatus(Long taskId, TaskStatusEnum newStatus) {
        log.info("[updateTaskStatus] Update task status for task with Id [{}]", taskId);

        Optional<Task> taskToBeDeleted = taskRepository.findById(taskId);
        taskToBeDeleted.ifPresentOrElse(task -> {
            if(checkValidStatusChange(task.getTaskStatus().getStatus(), newStatus.name())) {
                task.setTaskStatus(statusService.getStatus(newStatus.name()));
                task.setLastUpdateDate(LocalDateTime.now());
                taskRepository.save(task);
                log.info("[updateTaskStatus] Task Status Updated, new Status [{}]",
                        task.getTaskStatus().getStatus());
            } else {
                log.info("[updateTaskStatus] New status [{}] not allowed from the current status [{}]",
                        newStatus, taskToBeDeleted.get().getTaskStatus().getStatus());
                NonValidStatusChangeException.thrown();
            }
        }, TaskNotFoundException::thrown);
    }

    private boolean checkValidStatusChange(String currentStatus, String newStatus) {
        switch (currentStatus) {
            case "PENDING":
                return newStatus.equals("DOING");
            case "DOING":
                return newStatus.equals("BLOCKED") || newStatus.equals("DONE");
            case "BLOCKED":
                return newStatus.equals("DOING");
            case "DONE":
                return false;
            default:
                return false;
        }
    }
}
