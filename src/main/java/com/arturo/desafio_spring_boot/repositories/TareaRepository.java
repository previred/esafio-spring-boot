package com.arturo.desafio_spring_boot.repositories;

import org.springframework.stereotype.Repository;

import com.arturo.desafio_spring_boot.entities.EstadoTareaEntity;
import com.arturo.desafio_spring_boot.entities.TareaEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TareaRepository extends JpaRepository<TareaEntity, Long> {

    List<TareaEntity> findByEstado(EstadoTareaEntity estadoTareaEntity);

}
