package com.desafio.spring.service.impl;

import com.desafio.spring.mapper.TaskMapper;
import com.desafio.spring.repository.TaskRepository;
import org.openapitools.model.Task;
import com.desafio.spring.service.ITaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TaskServiceImpl implements ITaskService {

    private final TaskRepository repository;

    TaskServiceImpl(TaskRepository taskRepository) {
        this.repository = taskRepository;
    }

    @Override
    public void add(org.openapitools.model.Task model, long idUser, long idStatus) {
        model.setId(null);
        this.repository.save(
                TaskMapper.ModelToDAO(model, idUser, idStatus)
        );
    }

    @Override
    public Task update(org.openapitools.model.Task model, long idUser, long idStatus) {
        return TaskMapper.DaoToModel(
                this.repository.save(
                        TaskMapper.ModelToDAO(model, idUser, idStatus)
                )
        );
    }

    @Override
    public Task getById(String id) {
        return TaskMapper.DaoToModel(this.repository.getReferenceById(id));

    }

    @Override
    public void delete(String id) {
        this.repository.deleteById(id);
    }

    @Override
    public List<Task> getAll() {
        return TaskMapper.DaoToModel(this.repository.findAll());
    }
}
