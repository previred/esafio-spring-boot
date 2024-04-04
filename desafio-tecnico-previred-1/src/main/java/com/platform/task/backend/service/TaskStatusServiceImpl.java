package com.platform.task.backend.service;

import org.springframework.stereotype.Service;

import com.platform.task.backend.entity.TaskStatus;
import com.platform.task.backend.repository.TaskStatusRepository;
import com.platform.task.backend.service.TaskStatusService;

import java.util.List;
import java.util.Optional;

@Service
public class TaskStatusServiceImpl implements TaskStatusService {
	private final TaskStatusRepository taskStatusRepository;

	public TaskStatusServiceImpl(TaskStatusRepository taskStatusRepository) {
		this.taskStatusRepository = taskStatusRepository;
	}

	@Override
	public TaskStatus save(TaskStatus taskStatus) {
		return taskStatusRepository.save(taskStatus);
	}

	@Override
	public TaskStatus findById(Long id) {
		Optional<TaskStatus> optionalStatusTask = taskStatusRepository.findById(id);
		return optionalStatusTask.get();
	}

	@Override
	public List<TaskStatus> findStatusTask() {
		return taskStatusRepository.findAll();
	}

}
