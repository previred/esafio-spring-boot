package com.moveapps.pe.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
 
import com.moveapps.pe.entities.Task;
import com.moveapps.pe.entities.TaskState;
import com.moveapps.pe.repository.TaskRepository;
import com.moveapps.pe.repository.TaskStateRepository;
import com.moveapps.pe.service.TaskService;
import com.moveapps.pe.service.TaskStateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskStateServiceImpl implements TaskStateService {
	@Autowired
	private TaskStateRepository taskStateRepository;

	/**
	 * Method to get all the task
	 * 
	 * @return
	 */
	public List<TaskState> getTaskState() {
		return (List<TaskState>) taskStateRepository.findAll();
	}

	@Override
	public TaskState save(TaskState taskState) {
		// TODO Auto-generated method stub
		return taskStateRepository.save(taskState);
	}

	@Override
	public TaskState getTaskStateById(Long id) {
		// TODO Auto-generated method stub
		return taskStateRepository.findById(id).get();
	}

	@Override
	public TaskState delete(Long id) {
		// TODO Auto-generated method stub
		TaskState existTaskState = taskStateRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
		taskStateRepository.deleteById(id);
		return existTaskState;

	}

	@Override
	public TaskState update(TaskState taskState) {
		 
		return Optional.ofNullable(taskStateRepository.save(taskState))
				.orElseThrow(() -> new com.moveapps.pe.util.Exception("Mistake in update Task"));
	}
}
