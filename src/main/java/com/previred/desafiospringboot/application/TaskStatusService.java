package com.previred.desafiospringboot.application;

import com.previred.desafiospringboot.domain.model.TaskStatus;

import java.util.List;

public interface TaskStatusService {
    List<TaskStatus> getAllTaskStatus();
}

