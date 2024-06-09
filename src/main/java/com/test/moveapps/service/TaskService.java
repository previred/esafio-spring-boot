package com.test.moveapps.service;


import com.test.moveapps.domain.dto.TaskDto;
import com.test.moveapps.domain.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    TaskDto addTask(String taskName);

    List<TaskDto> getAllTasks();

    Optional<Task> findTaskById(Long taskId);

}
