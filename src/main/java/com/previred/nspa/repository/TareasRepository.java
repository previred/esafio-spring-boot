package com.previred.nspa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.previred.nspa.entity.Tareas;

@Repository
public interface TareasRepository extends JpaRepository<Tareas, Integer> {
}

