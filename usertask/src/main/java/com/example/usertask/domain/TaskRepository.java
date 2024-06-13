package com.example.usertask.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface TaskRepository extends CrudRepository<Task, Long> {
	// Fetch by task
	List<Task> findByTask(@Param("task") String task);

}
