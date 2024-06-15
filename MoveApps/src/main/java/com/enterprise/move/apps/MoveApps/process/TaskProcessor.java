package com.enterprise.move.apps.MoveApps.process;

import com.enterprise.move.apps.MoveApps.model.StatusTask;
import com.enterprise.move.apps.MoveApps.model.TaskModel;
import com.enterprise.move.apps.MoveApps.model.User;
import org.springframework.stereotype.Component;
import org.openapitools.model.Task;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskProcessor {

    public List<Task> buildTask(List<TaskModel> taskModelList) {
        return taskModelList.stream()
                .map(this::convertTaskModelToTask)
                .collect(Collectors.toList());
    }

    private Task convertTaskModelToTask(TaskModel taskModel) {
        Task task = new Task();
        task.setNameTask(taskModel.getNameTask());
        task.setIdTask(taskModel.getId().toString());
        task.setDescriptionTask(taskModel.getDescriptionTask());
        task.setTaskPriority(taskModel.getTaskPriority());
        task.setStatusTask(taskModel.getStatusTask().getNameStatusTask());
        task.setResponsible(taskModel.getUser().getName());
        return task;
    }


    public TaskModel convertTaskToTaskModel(Task task, User user, StatusTask statusTask) {
        TaskModel taskModel = new TaskModel();
        User user1 = new User();
        user1.setId(user.getId());

        StatusTask statusTask1 = new StatusTask();
        statusTask1.setId(statusTask.getId());

        taskModel.setNameTask(task.getNameTask());
        taskModel.setDescriptionTask(task.getDescriptionTask());
        taskModel.setTaskPriority(task.getTaskPriority());
        taskModel.setUser(user1);
        taskModel.setStatusTask(statusTask1);
        return taskModel;
    }

    public TaskModel convertTaskToTaskModelUpdate(Task task, User user, StatusTask statusTask) {
        TaskModel taskModel = new TaskModel();
        User user1 = new User();
        user1.setId(user.getId());

        StatusTask statusTask1 = new StatusTask();
        statusTask1.setId(statusTask.getId());

        taskModel.setId(Long.valueOf(task.getIdTask()));
        taskModel.setNameTask(task.getNameTask());
        taskModel.setDescriptionTask(task.getDescriptionTask());
        taskModel.setTaskPriority(task.getTaskPriority());
        taskModel.setUser(user1);
        taskModel.setStatusTask(statusTask1);

        return taskModel;
    }
}
