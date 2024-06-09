package com.test.moveapps.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssignTaskUpdateDto {

    public AssignTaskUpdateDto(){}

    private Long idAssignTask;
    private Long idTaskStatus;

    public AssignTaskUpdateDto(Long idAssignTask, Long idTaskStatus) {
        this.idAssignTask = idAssignTask;
        this.idTaskStatus = idTaskStatus;
    }

    public Long getIdAssignTask() {
        return idAssignTask;
    }

    public void setIdAssignTask(Long idAssignTask) {
        this.idAssignTask = idAssignTask;
    }

    public Long getIdTaskStatus() {
        return idTaskStatus;
    }

    public void setIdTaskStatus(Long idTaskStatus) {
        this.idTaskStatus = idTaskStatus;
    }

}
