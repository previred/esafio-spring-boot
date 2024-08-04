package com.desafio.task.service;

import com.desafio.task.entity.Task;
import com.desafio.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;

    @Override
    public Task save(Task task) throws Exception {
        return repository.save(task);
    }

    @Override
    public Task update(Task task, Integer integer) throws Exception {
        return null;
    }

    @Override
    public List<Task> readAll() throws Exception {
        return null;
    }

    @Override
    public void delete(Integer integer) throws Exception {

    }
}
