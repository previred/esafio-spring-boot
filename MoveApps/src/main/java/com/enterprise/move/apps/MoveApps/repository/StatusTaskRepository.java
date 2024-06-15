package com.enterprise.move.apps.MoveApps.repository;


import com.enterprise.move.apps.MoveApps.model.StatusTask;
import org.springframework.data.repository.CrudRepository;

public interface StatusTaskRepository extends CrudRepository<StatusTask, Long> {

    StatusTask findByNameStatusTask(String nameStatusTask);
}
