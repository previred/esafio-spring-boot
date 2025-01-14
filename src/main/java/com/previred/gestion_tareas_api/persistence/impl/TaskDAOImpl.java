package com.previred.gestion_tareas_api.persistence.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.previred.gestion_tareas_api.entities.Task;
import com.previred.gestion_tareas_api.persistence.TaskDAO;
import com.previred.gestion_tareas_api.repositories.TaskRepository;


@Component
public class TaskDAOImpl implements TaskDAO {

    @Autowired
    private TaskRepository taskRepository;


    @Override
    public List<Task> findAll() {

        return taskRepository.findAll();
    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public void save(Task task) {
        taskRepository.save(task);
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

}
