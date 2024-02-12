package com.move.challenge.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.move.challenge.entity.EstadoTareaEntity;
import com.move.challenge.entity.TareaEntity;
import com.move.challenge.entity.UsuarioEntity;

public interface TareaRepository extends JpaRepository<TareaEntity, Long> {

   @Query("SELECT t FROM TareaEntity t WHERE t.usuario.id = :usuarioId")
   List<TareaEntity> findAllByUsuarioId(@Param("usuarioId") Long userId);

   @Modifying
   @Query("UPDATE TareaEntity t SET t.estado = :estado WHERE t.id = :tareaId")
   void updateTareaEstado(@Param("tareaId") Long tareaId, @Param("estado") EstadoTareaEntity estado);

   @Modifying
   @Query("UPDATE TareaEntity t SET t.usuario = :usuario WHERE t.id = :tareaId")
   void updateTareaUsuario(@Param("tareaId") Long tareaId, @Param("usuario") UsuarioEntity usuario);

   @Modifying
   @Query("UPDATE TareaEntity t SET t.fechaFinalizacion = :fechaFinalizacion WHERE t.id = :tareaId")
   void updateTareaFinalizacion(@Param("tareaId") Long tareaId, @Param("fechaFinalizacion") LocalDateTime fechaFinalizacion);

}