package smarroquin.desafiospringboot.services;

import java.util.List;

import smarroquin.desafiospringboot.controllers.ResponseBase;
import smarroquin.desafiospringboot.entities.DTO.TaskDTO;

public interface TaskService {
	
	public List<TaskDTO> getTasks();
	public TaskDTO getTask(long id);
	
	public ResponseBase createTask(TaskDTO taskDTO);
	public ResponseBase updateTask(TaskDTO taskDTO);
	public ResponseBase deleteTask(long taskId);

}