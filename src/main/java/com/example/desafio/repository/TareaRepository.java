package com.example.desafio.repository;

import com.example.desafio.model.entities.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareaRepository extends JpaRepository<Tarea, Integer> {
}
