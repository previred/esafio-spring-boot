package com.company.service.task;

import com.company.model.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {

    public void create(Task task);

    public void update(Task task);

    public void delete(UUID uuid);

    public Optional<Task> findById(UUID uuid);

    public List<Task> getAll();
}
