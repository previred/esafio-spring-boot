package cl.previred.desafip.msprevireddesafio.services;

import cl.previred.desafip.msprevireddesafio.entities.Task;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskService {

  List<Task> getAllTasks();
  Task getTaskById(Long id);
  Task addTask(Task task);
  Task updateTask(Task task);
  boolean deleteTask(Long id);

}

