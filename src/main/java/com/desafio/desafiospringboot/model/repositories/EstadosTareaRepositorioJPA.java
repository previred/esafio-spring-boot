package com.desafio.desafiospringboot.model.repositories;

import com.desafio.desafiospringboot.model.dao.EstadosTarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstadosTareaRepositorioJPA extends JpaRepository<EstadosTarea,Long> {
    @Query(value = "SELECT e FROM EstadosTarea e WHERE e.id=?1")
    Optional<EstadosTarea> buscarEstado(Long estadoTareaID);

    @Query(value = "SELECT COUNT(*) FROM EstadosTarea e")
    Long contarEstados();


}
