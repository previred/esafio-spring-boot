package com.previred.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.previred.challenge.model.Tareas;

@Repository
public interface TareasRepository extends JpaRepository<Tareas, Integer> {
}
