package com.previred.desafio.repository;

import com.previred.desafio.entity.EstadoTarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoTareaRepository extends JpaRepository<EstadoTarea, Long> { }
