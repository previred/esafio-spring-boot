package com.platform.task.backend.repository;

import com.platform.task.backend.entity.Task;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	@Query("select t from Task t where t.usuario.id = :id")
	Stream<Task> listByUsuario(@Param("id") Long idUsuario);

}
