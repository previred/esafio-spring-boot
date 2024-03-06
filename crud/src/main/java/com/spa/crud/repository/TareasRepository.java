package com.spa.crud.repository;

import com.spa.crud.model.Tareas;
import com.spa.crud.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TareasRepository extends JpaRepository<Tareas, Long> {
    void deleteById(@Param("id") Long id);

    Optional<Tareas> findByNumeroTarea(@Param("numeroTarea") Long numeroTarea);

    @Query("SELECT s FROM Tareas s")
    List<Tareas> getAll();

    @Query("SELECT s FROM Tareas s where s.id = :id")
    Tareas getAllById(@Param("id") Long id);

    @Query("SELECT MAX(t.numeroTarea) FROM Tareas t")
    int getLastNumberTask();

    @Query("SELECT t.id from Tareas t where t.numeroTarea = :numeroTarea")
    Long getIdByNumeroTarea(@Param("numeroTarea") String numeroTarea);
}
