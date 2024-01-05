package com.moveapps.taskmanager.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.moveapps.taskmanager.entity.TaskState.TaskStatus;
import java.time.LocalDateTime;

public record TaskStateDTO(@JsonIgnore Long id, String externalId, Long taskId, TaskStatus taskStatus, LocalDateTime createdAt, LocalDateTime modifiedAt) {

}
