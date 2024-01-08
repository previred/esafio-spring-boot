package com.previred.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.previred.entities.UsuarioEntity;


/**
 * @author cristian
 */
@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity,String> { 
	
}
