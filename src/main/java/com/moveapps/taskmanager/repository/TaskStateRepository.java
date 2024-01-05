package com.moveapps.taskmanager.repository;

import com.moveapps.taskmanager.entity.TaskState;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskStateRepository extends CrudRepository<TaskState, Long> {

    TaskState findByExternalIdEquals(String externalId);

}
