package com.desafio.spring.desafiospringboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.spring.desafiospringboot.model.EstadoTarea;

@Repository
public interface EstadoTareaRepository extends JpaRepository<EstadoTarea, Long>{

}
