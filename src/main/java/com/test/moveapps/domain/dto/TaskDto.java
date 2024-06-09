package com.test.moveapps.domain.dto;

import com.test.moveapps.domain.entity.Task;

public class TaskDto {

    public TaskDto(){}

    private Long idTask;
    private String taskName;

    public TaskDto(Long idTask, String taskName) {
        this.idTask = idTask;
        this.taskName = taskName;
    }

    public Long getIdTask() {
        return idTask;
    }

    public void setIdTask(Long idTask) {
        this.idTask = idTask;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public TaskDto convertTask(Task task){
        return new TaskDto( task.getId(), task.getTask_name());
    }

}
