package com.platform.task.backend.service;

import java.util.List;

import com.platform.task.backend.entity.TaskStatus;

public interface TaskStatusService {

	TaskStatus save(TaskStatus taskStatus);

	TaskStatus findById(Long id);

	List<TaskStatus> findStatusTask();

}
