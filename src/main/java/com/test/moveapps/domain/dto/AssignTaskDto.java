package com.test.moveapps.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssignTaskDto {

    public AssignTaskDto(){}

    private Long idAssignTask;
    private Long idUserAssignTask;
    private String userAssignTask;
    private Long idTaskStatus;
    private String taskStatus;
    private Long idTask;
    private String taskName;


    public AssignTaskDto(Long idAssignTask, Long idUserAssignTask, Long idTaskStatus, Long idTask) {
        this.idAssignTask = idAssignTask;
        this.idUserAssignTask = idUserAssignTask;
        this.idTaskStatus = idTaskStatus;
        this.idTask = idTask;
    }

    public Long getIdAssignTask() {
        return idAssignTask;
    }

    public void setIdAssignTask(Long idAssignTask) {
        this.idAssignTask = idAssignTask;
    }

    public Long getIdUserAssignTask() {
        return idUserAssignTask;
    }

    public void setIdUserAssignTask(Long idUserAssignTask) {
        this.idUserAssignTask = idUserAssignTask;
    }

    public String getUserAssignTask() {
        return userAssignTask;
    }

    public void setUserAssignTask(String userAssignTask) {
        this.userAssignTask = userAssignTask;
    }

    public Long getIdTaskStatus() {
        return idTaskStatus;
    }

    public void setIdTaskStatus(Long idTaskStatus) {
        this.idTaskStatus = idTaskStatus;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
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
}
