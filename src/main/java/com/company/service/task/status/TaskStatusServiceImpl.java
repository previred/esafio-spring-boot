package com.company.service.task.status;

import com.company.persistence.task.status.TaskStatusDataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TaskStatusServiceImpl implements  TaskStatusService{


    private final TaskStatusDataProvider taskStatusDataProvider;


    @Override
    public void validTaskStatus(UUID uuid) {
        taskStatusDataProvider.validTaskStatus(uuid);
    }
}
