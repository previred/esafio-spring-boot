package com.nuevospa.taskmanager.application;

import com.nuevospa.taskmanager.domain.model.Tareas;
import com.nuevospa.taskmanager.domain.repository.TareasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareasService {

    @Autowired
    private TareasRepository tareasRepository;

    public List<Tareas> getAllTasks() {
        return tareasRepository.findAll();
    }

    public Optional<Tareas> getTaskById(Long id) {
        return tareasRepository.findById(id);
    }

    public Tareas createTask(Tareas task) {
        return tareasRepository.save(task);
    }

    public Tareas updateTask(Tareas task) {
        return tareasRepository.save(task);
    }

    public void deleteTask(Long id) {
        tareasRepository.deleteById(id);
    }

}
