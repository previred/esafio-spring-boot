package com.moveapps.management.task.domains.services;

import static com.moveapps.commons.api.applications.model.StatusDomain.DELETE;
import static com.moveapps.management.user.infraestructure.confing.Constants.MANAGEMENT_MODULE;
import static com.moveapps.management.user.infraestructure.confing.Constants.TASK_SERVICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.moveapps.commons.api.domains.services.EndPointServiceImpl;
import com.moveapps.management.task.domains.data.TaskDTO;
import com.moveapps.management.task.infraestructure.entities.TaskEntity;
import com.moveapps.management.task.infraestructure.repositories.TaskRepository;

@Service
public class TaskService   extends EndPointServiceImpl< TaskDTO, TaskEntity, String>  {
	@Autowired
	TaskRepository repository;
	
	@Override
	public JpaRepository<TaskEntity, String> getDao() {
		return this.repository;
	}

	@Override
	public TaskEntity statusChangeDelete(TaskEntity entity) {
		entity.setStatus(DELETE);
		return entity;
	}

	@Override
	public String nameModule() {
		return MANAGEMENT_MODULE;
	}

	@Override
	public String className() {
		return TASK_SERVICE;
	}

}
