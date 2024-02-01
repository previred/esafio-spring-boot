package com.desafio.spring.desafiospringboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.spring.desafiospringboot.model.Tarea;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long>{
	
	Tarea findOneByNombreTarea(String nombre);

}
