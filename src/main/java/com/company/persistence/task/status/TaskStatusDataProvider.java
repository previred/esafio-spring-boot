package com.company.persistence.task.status;

import com.company.exception.AppException;
import com.company.exception.enums.CodeExceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class TaskStatusDataProvider {

    private final TaskStatusRepository taskStatusRepository;

    public void validTaskStatus(UUID uuid) {
        taskStatusRepository.findById(uuid).orElseThrow(()-> new AppException(CodeExceptions.TASK_STATUS_NOT_FOUND));
    }

}
