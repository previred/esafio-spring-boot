package com.enterprise.move.apps.MoveApps.repository;


import com.enterprise.move.apps.MoveApps.model.TaskModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  TaskRepository  extends CrudRepository<TaskModel, Long> {

    List<TaskModel> findAll();


}
