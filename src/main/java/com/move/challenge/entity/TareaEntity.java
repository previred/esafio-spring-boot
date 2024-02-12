package com.move.challenge.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.Data;

@Data
@Entity
@Table(name = "tareas")
@EntityListeners(AuditingEntityListener.class)
public class TareaEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String nombre;

   private String descripcion;

   @CreatedDate
   @Column(name = "fecha_creacion")
   private LocalDateTime fechaCreacion;

   @CreatedBy
   @Column(name = "creado_por")
   private String creadoPor;

   @LastModifiedDate
   @Column(name = "fecha_modificacion")
   private LocalDateTime fechaModificacion;

   @LastModifiedBy
   @Column(name = "modificado_por")
   private String modificadoPor;

   @Column(name = "fecha_finalizacion")
   private LocalDateTime fechaFinalizacion;

   @ManyToOne
   @JoinColumn(name = "usuario_id")
   private UsuarioEntity usuario;

   @ManyToOne
   @JoinColumn(name = "estado_id")
   private EstadoTareaEntity estado;

   @PrePersist
   public void prePersist() {
      String usuarioActual = SecurityContextHolder.getContext().getAuthentication().getName();
      this.creadoPor = usuarioActual;
      this.modificadoPor = usuarioActual;
   }

   @PreUpdate
   public void preUpdate() {
      String usuarioActual = SecurityContextHolder.getContext().getAuthentication().getName();
      this.modificadoPor = usuarioActual;
   }

}