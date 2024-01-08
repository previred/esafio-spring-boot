package com.previred.repositories;



import org.springframework.stereotype.Repository;

import com.previred.entities.TareaEntity;

import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author cristian
 */
@Repository
public interface TareaRepository extends JpaRepository<TareaEntity,Long> { 
	
}

