package com.desafio.desafiospringboot.model.repositories;

import com.desafio.desafiospringboot.model.dao.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TareaRepositorioJPA extends JpaRepository<Tarea,Long> {

    @Query(value = "SELECT t FROM Tarea t WHERE t.usuario.id=?1")
    List<Tarea> listarTareas(Long idUser);


}
