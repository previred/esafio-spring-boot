package com.springboot.desafio.repository;

import com.springboot.desafio.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareaRepository extends JpaRepository<Tarea, Long> {

}
