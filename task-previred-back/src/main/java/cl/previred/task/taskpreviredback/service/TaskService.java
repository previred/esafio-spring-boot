package cl.previred.task.taskpreviredback.service;

import cl.previred.task.taskpreviredback.domain.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<Task> getTask();

    Task getTaskById(Long id);

    List<Task> getTaskByUser(Long idGrade);

    Optional<Task> checkTaskById(Long id);

    Task addNewTask(Task task);

    void deleteTask(Long taskId);

    Task updateTask(Task task);
}
