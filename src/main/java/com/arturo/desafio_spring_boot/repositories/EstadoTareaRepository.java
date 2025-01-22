package com.arturo.desafio_spring_boot.repositories;

import org.springframework.stereotype.Repository;

import com.arturo.desafio_spring_boot.entities.EstadoTareaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EstadoTareaRepository extends JpaRepository<EstadoTareaEntity, Long> {

}
