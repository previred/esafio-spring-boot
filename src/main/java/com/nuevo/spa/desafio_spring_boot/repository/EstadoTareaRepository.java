package com.nuevo.spa.desafio_spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nuevo.spa.desafio_spring_boot.model.EstadoTarea;

public interface EstadoTareaRepository extends JpaRepository<EstadoTarea, Long>  {

}
