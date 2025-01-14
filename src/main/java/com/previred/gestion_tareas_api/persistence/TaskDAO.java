package com.previred.gestion_tareas_api.persistence;

import java.util.List;
import java.util.Optional;

import com.previred.gestion_tareas_api.entities.Task;

public interface TaskDAO {

    List<Task> findAll();

    Optional<Task> findById(Long id);

    void save(Task task);

    void deleteById(Long id);

}
