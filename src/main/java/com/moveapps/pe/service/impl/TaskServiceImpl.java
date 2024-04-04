package com.moveapps.pe.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
 
import com.moveapps.pe.entities.Task; 
import com.moveapps.pe.repository.TaskRepository; 
import com.moveapps.pe.service.TaskService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
	@Autowired
	private TaskRepository taskRepository;

	/**
	 * Method to get all the task
	 * 
	 * @return
	 */
	public List<Task> getTask() {
		return (List<Task>) taskRepository.findAll();
	}

	@Override
	public Task save(Task task) {
		// TODO Auto-generated method stub
		return taskRepository.save(task);
	}

	@Override
	public Task getTaskById(Long id) {
		// TODO Auto-generated method stub
		return taskRepository.findById(id).get();
	}

	@Override
	public Task delete(Long id) {
		// TODO Auto-generated method stub
		Task existTask = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
		taskRepository.deleteById(id);
		return existTask;

	}

	@Override
	public Task update(Task task) {
		Task existTask = taskRepository.findById(task.getId()).orElseThrow(() -> new RuntimeException("Not Found"));
		if (existTask != null) {
			existTask.setTaskState(task.getTaskState());
			existTask.setDescription(task.getDescription());
			existTask.setUser(task.getUser());
			//taskRepository.save(task);

		}

		return Optional.ofNullable(taskRepository.save(existTask))
				.orElseThrow(() -> new com.moveapps.pe.util.Exception("Mistake in update Task"));
	}
}
