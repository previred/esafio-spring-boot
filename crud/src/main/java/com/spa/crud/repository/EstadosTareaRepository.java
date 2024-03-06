package com.spa.crud.repository;

import com.spa.crud.model.EstadosTarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadosTareaRepository extends JpaRepository<EstadosTarea, Integer>  {

    @Query("SELECT s FROM EstadosTarea s WHERE s.nombreEstado = :status")
    EstadosTarea findStatusByName(@Param("status") String status);

    @Query("SELECT s.idEstado, s.nombreEstado FROM EstadosTarea s ")
    List<String> getEstados();
}
