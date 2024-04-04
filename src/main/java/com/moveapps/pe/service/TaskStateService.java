package com.moveapps.pe.service;

import java.util.List;


import com.moveapps.pe.entities.Task;
import com.moveapps.pe.entities.TaskState;

public interface TaskStateService {
	public List<TaskState> getTaskState();
	public TaskState save(TaskState task);
	public TaskState getTaskStateById(Long id);
	public TaskState delete(Long id);
	public TaskState update(TaskState task);
}
