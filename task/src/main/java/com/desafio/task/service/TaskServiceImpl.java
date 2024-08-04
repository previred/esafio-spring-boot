package com.desafio.task.service;

import com.desafio.task.entity.Task;
import com.desafio.task.exception.ModelNotFoundException;
import com.desafio.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;

    @Override
    public Task save(Task task) throws Exception {
        return repository.save(task);
    }

    @Override
    public Task update(Task task, Long id) throws Exception {
        Task taskFromBD = repository.findById(id).orElseThrow(()-> new ModelNotFoundException("ID NOT FOUND " + id));

        if(Objects.nonNull(task.getName()) && !"".equalsIgnoreCase(task.getName())){
            taskFromBD.setName(task.getName());
        }
        if(Objects.nonNull(task.getDescription()) && !"".equalsIgnoreCase(task.getDescription())){
            taskFromBD.setDescription(task.getDescription());
        }
        if(Objects.nonNull(task.getState())){
            taskFromBD.setState(task.getState());
        }

        return repository.save(taskFromBD);
    }

    @Override
    public List<Task> readAll() throws Exception {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) throws Exception {
        repository.deleteById(id);
    }
}
