package cl.previred.tasksapi.service;

import org.openapitools.model.Task;
import org.openapitools.model.TaskInput;

import java.util.List;

public interface TasksService {

    /**
     *
     * @return
     */
    List<Task> getAllTasks();

    /**
     *
     * @param input
     * @param taskId
     * @return
     */
    Task updateTask(TaskInput input, int taskId);

    /**
     *
     * @param input
     * @return
     */
    Task newTask(TaskInput input);

    /**
     *
     * @param taskId
     * @return
     */
    Task getTask(int taskId);

    /**
     *
     * @param taskName
     * @return
     */
    Task getTask(String taskName);

    /**
     *
     * @param taskId
     * @return
     */
    Boolean deleteTask(int taskId);

    /**
     *
     * @param taskName
     * @return
     */
    Boolean deleteTask(String taskName);


}
