package com.test.previred.domain.model.task.gateway;

import com.test.previred.domain.model.task.Task;

import java.util.List;

public interface TaskRepository {

    Task save(Task task);

    List<Task> findAll();

    Task findById(String id);

    void deleteById(String id);

    Task update(Task task);
}
