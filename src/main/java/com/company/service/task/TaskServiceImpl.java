package com.company.service.task;

import com.company.exception.AppException;
import com.company.exception.enums.CodeExceptions;
import com.company.model.Task;
import com.company.persistence.task.TaskDataProvider;
import com.company.service.task.status.TaskStatusService;
import com.company.service.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskDataProvider taskDataProvider;
    private final TaskStatusService taskStatusService;
    private final UserService userService;


    @Override
    public void create(Task task) {
        taskStatusService.validTaskStatus(task.getTaskStatusId());
        userService.validUserId(task.getUserId());
        taskDataProvider.create(task);
    }

    @Override
    public void update(Task task) {
        validTask(task.getUuid());
        taskStatusService.validTaskStatus(task.getTaskStatusId());
        userService.validUserId(task.getUserId());
        taskDataProvider.create(task);
    }

    @Override
    public void delete(UUID uuid) {
        validTask(uuid);
        taskDataProvider.delete(uuid);
    }

    @Override
    public Optional<Task> findById(UUID uuid) {
        return taskDataProvider.findById(uuid);
    }

    @Override
    public List<Task> getAll() {
        return taskDataProvider.getAll();
    }

    private  void validTask(UUID uuid) {
        taskDataProvider.findById(uuid)
                        .orElseThrow(() -> new AppException(CodeExceptions.TASK_NOT_FOUND));
    }
}
