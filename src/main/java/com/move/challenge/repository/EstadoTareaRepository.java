package com.move.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.move.challenge.entity.EstadoTareaEntity;

public interface EstadoTareaRepository extends JpaRepository<EstadoTareaEntity, Long> {
}