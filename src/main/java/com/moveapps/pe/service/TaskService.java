package com.moveapps.pe.service;

import java.util.List;


import com.moveapps.pe.entities.Task;

public interface TaskService {
	public List<Task> getTask();
	public Task save(Task task);
	public Task getTaskById(Long id);
	public Task delete(Long id);
	public Task update(Task task);
}
