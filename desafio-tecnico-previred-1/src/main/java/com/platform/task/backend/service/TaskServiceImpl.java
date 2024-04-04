package com.platform.task.backend.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.task.backend.entity.Task;
import com.platform.task.backend.exceptions.RequestException;
import com.platform.task.backend.repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	TaskRepository taskRepository;

	@Override
	public List<Task> findAllTasks() {
		return taskRepository.findAll();
	}

	@Override
	public Task saveTask(Task task) {
		return taskRepository.save(task);
	}

	@Override
	public Task updateTask(Task task) {
		Task existingTask = taskRepository.findById(task.getId())
				.orElseThrow(() -> new NoSuchElementException("Task no encontrada"));

		existingTask.setTaskStatus(task.getTaskStatus());
		existingTask.setDescription(task.getDescription());
		existingTask.setUsuario(task.getUsuario());
		return Optional.ofNullable(taskRepository.save(task))
				.orElseThrow(() -> new RequestException("Error al modificar tarea"));
	}

	public void deleteTask(Long id) {
		taskRepository.deleteById(id);
	}

	@Override
	public List<Task> listTareasByUuarioId(Long idUsuario) {
		return taskRepository.listByUsuario(idUsuario).collect(Collectors.toList());
	}

	@Override
	public Task findById(Long id) {
		return taskRepository.findById(id)
				.orElseThrow(() -> new RequestException("No se encuentra Tarea con id : " + id));
	}

}
