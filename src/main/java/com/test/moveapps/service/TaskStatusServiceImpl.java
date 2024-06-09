package com.test.moveapps.service;

import com.test.moveapps.domain.entity.TaskStatus;
import com.test.moveapps.repository.TaskStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskStatusServiceImpl implements TaskStatusService {

    @Autowired
    TaskStatusRepository taskStatusRepository;

    @Override
    public Optional<TaskStatus> findById(Long id) {
        return taskStatusRepository.findById(id);
    }
}
