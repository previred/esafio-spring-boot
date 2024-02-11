package com.previred.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.previred.challenge.model.EstadosTarea;

@Repository
public interface EstadosTareaRepository extends JpaRepository<EstadosTarea, Integer> {
	@Query("SELECT s FROM EstadosTarea s WHERE s.status = :status")
	EstadosTarea findStatusByName(@Param("status") String status);
}
