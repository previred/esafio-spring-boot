package com.previred.desafioApiRest.desafioprevired.repository;

import com.previred.desafioApiRest.desafioprevired.repository.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {

    @Query("select t from Tarea t where t.usuario.id = :id")
    Stream<Tarea> listByUsuario(@Param("id") Long idUsuario);

}
