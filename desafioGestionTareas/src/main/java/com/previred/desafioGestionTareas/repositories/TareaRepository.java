package com.previred.desafioGestionTareas.repositories;

import com.previred.desafioGestionTareas.entities.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {

}
