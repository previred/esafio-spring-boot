package com.previred.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.previred.entities.EstadoTareaEntity;


/**
 * @author cristian
 */
@Repository
public interface EstadoTareaRepository extends JpaRepository<EstadoTareaEntity,Long> { 
	
}
