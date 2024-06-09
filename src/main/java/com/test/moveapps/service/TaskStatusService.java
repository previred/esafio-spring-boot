package com.test.moveapps.service;

import com.test.moveapps.domain.entity.TaskStatus;

import java.util.Optional;

public interface TaskStatusService {

    Optional<TaskStatus> findById(Long id);

}
