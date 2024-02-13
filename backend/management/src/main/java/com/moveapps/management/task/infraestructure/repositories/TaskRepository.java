package com.moveapps.management.task.infraestructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moveapps.management.task.infraestructure.entities.TaskEntity;
import com.moveapps.management.user.infraestructure.entities.UserEntity;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, String>{

}
