package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.EstadoTarea;

@Repository
public interface EstadoTareaRepository extends JpaRepository<EstadoTarea, Long> {
    // Métodos personalizados si es necesario
}