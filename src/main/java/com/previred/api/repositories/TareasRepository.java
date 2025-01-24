package com.previred.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.previred.api.models.Tareas;

@Repository
public interface TareasRepository extends JpaRepository<Tareas, Long> {
}
