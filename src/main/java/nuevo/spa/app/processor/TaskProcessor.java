package nuevo.spa.app.processor;

import com.spring.nuevo.spa.app.model.TaskListResponse;
import com.spring.nuevo.spa.app.model.TaskRequest;
import com.spring.nuevo.spa.app.model.TaskResponse;
import nuevo.spa.app.entity.Task;
import nuevo.spa.app.entity.TaskStatus;


public class TaskProcessor {

    public static TaskListResponse toTaskListResponse(Task task) {

        TaskListResponse taskResponse = new TaskListResponse();

        taskResponse.setId(task.getId());
        taskResponse.setTitle(task.getTitle());
        taskResponse.setDetail(task.getDetail());
        taskResponse.setStatus(task.getStatus().getValueStatus());

        return taskResponse;
    }


    public static TaskResponse toTaskResponse(Task task) {

        TaskResponse taskResponse = new TaskResponse();

        taskResponse.setId(task.getId());

        return taskResponse;
    }


    public static Task toTask(TaskRequest request) {

        Task task = new Task();

        TaskStatus taskStatus = new TaskStatus();

        task.setId(request.getId());
        task.setTitle(request.getTitle());
        task.setDetail(request.getDetail());

        if(request.getStatus() != null){
            taskStatus.setId(Long.valueOf(request.getStatus()));
        }
        task.setStatus(taskStatus);

        return task;
    }

}
