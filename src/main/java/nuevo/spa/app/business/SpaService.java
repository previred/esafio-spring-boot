package nuevo.spa.app.business;

import com.spring.nuevo.spa.app.model.TaskListResponse;
import com.spring.nuevo.spa.app.model.TaskRequest;
import com.spring.nuevo.spa.app.model.TaskResponse;

import java.util.List;

public interface SpaService {

    List<TaskListResponse> getTask();

    TaskResponse createTask(TaskRequest request);

    TaskResponse updateTask(String id, TaskRequest request);

    void deleteTask(String id);

}
