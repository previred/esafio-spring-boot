package com.moveapps.management.statustask.domains.services;

import static com.moveapps.commons.api.applications.model.StatusDomain.DELETE;
import static com.moveapps.management.user.infraestructure.confing.Constants.MANAGEMENT_MODULE;
import static com.moveapps.management.user.infraestructure.confing.Constants.STATUSTASK_SERVICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.moveapps.commons.api.domains.services.EndPointServiceImpl;
import com.moveapps.management.statustask.domains.data.StatusTaskDTO;
import com.moveapps.management.statustask.infraestructure.entities.StatusTaskEntity;
import com.moveapps.management.statustask.infraestructure.repositories.StatusTaskRepository;


@Service
public class StatusTaskService extends EndPointServiceImpl< StatusTaskDTO, StatusTaskEntity, String>{
	@Autowired
	StatusTaskRepository repository;

	@Override
	public JpaRepository<StatusTaskEntity, String> getDao() {
		return this.repository;
	}

	@Override
	public StatusTaskEntity statusChangeDelete(StatusTaskEntity entity) {
		entity.setStatus(DELETE);
		return entity;
	}

	@Override
	public String nameModule() {
		return MANAGEMENT_MODULE;
	}

	@Override
	public String className() {
		return STATUSTASK_SERVICE;
	}

}
