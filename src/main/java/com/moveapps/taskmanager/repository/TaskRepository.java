package com.moveapps.taskmanager.repository;

import com.moveapps.taskmanager.entity.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

    Task findByExternalIdEquals(String externalId);
}
