package com.previred.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.previred.api.models.EstadosTarea;

@Repository
public interface EstadosTareaRepository extends JpaRepository<EstadosTarea, Long> {
}
