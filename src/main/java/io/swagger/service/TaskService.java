package io.swagger.service;

import io.swagger.entity.TaskEntity;
import io.swagger.entity.TaskStatusEntity;
import io.swagger.exception.TaskNotFoundException;
import io.swagger.exception.TaskStatusNotFoundException;
import io.swagger.model.Task;
import io.swagger.model.TaskPage;
import io.swagger.model.TaskResponse;
import io.swagger.model.TaskStatus;
import io.swagger.repository.TaskRepository;
import io.swagger.repository.TaskStatusRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskStatusRepository taskStatusRepository;
    private final TaskRepository taskRepository;

    public TaskResponse addTask(Task task) {
        TaskStatusEntity taskStatus = findTaskStatus(task.getStatus());

        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle(task.getTitle());
        taskEntity.setDescription(task.getDescription());
        taskEntity.setStatus(taskStatus);

        var savedTask = taskRepository.save(taskEntity);

        return toResponse(savedTask);
    }

    public void deleteTask(Long taskId) {
        taskRepository.findById(taskId);

        taskRepository.deleteById(taskId);
    }

    public TaskResponse getTaskById(Long taskId) {
        TaskEntity taskEntity = findById(taskId);

        return toResponse(taskEntity);
    }

    private TaskEntity findById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
    }

    private TaskStatusEntity findTaskStatus(Long taskStatusId) {
        return taskStatusRepository.findById(taskStatusId)
                .orElseThrow(() -> new TaskStatusNotFoundException(taskStatusId));
    }

    private TaskResponse toResponse(TaskEntity task) {
        return new TaskResponse()
                .taskId(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(toResponse(task.getStatus()));
    }

    private TaskStatus toResponse(TaskStatusEntity status) {
        return new TaskStatus().statusId(status.getId()).description(status.getDescription());
    }

    public TaskResponse updateTaskWithForm(
            Long taskId,
            String title,
            String description,
            Long status) {

        TaskEntity taskEntity = findById(taskId);

        if (StringUtils.isNotBlank(title)) {
            taskEntity.setTitle(title);
        }

        if (StringUtils.isNotBlank(description)) {
            taskEntity.setDescription(description);
        }

        if (status != null) {
            TaskStatusEntity taskStatus = findTaskStatus(status);
            taskEntity.setStatus(taskStatus);
        }

        TaskEntity savedTask = taskRepository.save(taskEntity);

        return toResponse(savedTask);
    }

    public TaskResponse updateTask(Long taskId, Task body) {

        TaskEntity taskEntity = findById(taskId);

        taskEntity.setTitle(body.getTitle());
        taskEntity.setDescription(body.getDescription());

        TaskStatusEntity taskStatus = findTaskStatus(body.getStatus());
        taskEntity.setStatus(taskStatus);

        TaskEntity savedTask = taskRepository.save(taskEntity);

        return toResponse(savedTask);
    }

    public TaskPage listTasks(Long page, Long size, String title, String description, Long status) {
        Pageable pageable = PageRequest.of(page.intValue() - 1, size.intValue());
        Page<TaskEntity> tasks = taskRepository.filter(title, description, status, pageable);
        return new TaskPage()
                .total(tasks.getTotalElements())
                .items(mapToItems(tasks.getContent()));
    }

    private List<TaskResponse> mapToItems(List<TaskEntity> content) {
        return content.stream().map(this::toResponse).collect(Collectors.toList());
    }
}
