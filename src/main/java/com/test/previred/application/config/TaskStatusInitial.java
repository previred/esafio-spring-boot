package com.test.previred.application.config;

import com.test.previred.infrastructure.adapter.h2.enums.TaskStatusEnum;
import com.test.previred.infrastructure.adapter.h2.task.TaskStatusDataRepository;
import com.test.previred.infrastructure.adapter.h2.task.TaskStatusEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
public class TaskStatusInitial {

    private final TaskStatusDataRepository taskStatusRepository;

    @Autowired
    public TaskStatusInitial(TaskStatusDataRepository taskStatusRepository) {
        this.taskStatusRepository = taskStatusRepository;
    }

    @PostConstruct
    public void init() {
        Arrays.stream(TaskStatusEnum.values())
                .forEach(
                        taskStatus ->
                                taskStatusRepository.save(
                                        TaskStatusEntity.builder()
                                                .name(taskStatus.getLabel())
                                                .build()));
    }
}