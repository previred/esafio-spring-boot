package com.previred.desafio_spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.previred.desafio_spring_boot.Domain.Tarea;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {

}

