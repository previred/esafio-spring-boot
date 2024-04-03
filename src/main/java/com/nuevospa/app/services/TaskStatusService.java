package com.nuevospa.app.services;

import com.nuevospa.app.entities.TaskStatus;

public interface TaskStatusService {
    TaskStatus getTaskStatusByName(String name);
}