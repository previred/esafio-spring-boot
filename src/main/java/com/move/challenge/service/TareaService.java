package com.move.challenge.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.move.challenge.dto.TareaCreateDto;
import com.move.challenge.dto.TareaDto;
import com.move.challenge.dto.mapper.TareaMapper;
import com.move.challenge.entity.TareaEntity;
import com.move.challenge.repository.EstadoTareaRepository;
import com.move.challenge.repository.TareaRepository;
import com.move.challenge.repository.UsuarioRepository;
import com.move.challenge.utils.exception.ChallengeExceptionCode;
import com.move.challenge.utils.exception.GlobalChallengeException;

@Service
public class TareaService {

   @Autowired
   private TareaRepository tareaRepository;

   @Autowired
   private EstadoTareaRepository estadoTareaRepository;

   @Autowired
   private UsuarioRepository usuarioRepository;

   public TareaDto createTarea(TareaCreateDto tareaDto) {
      TareaEntity entity = TareaMapper.INSTANCE.toTareaEntity(tareaDto);
      return TareaMapper.INSTANCE.toTareaDto(tareaRepository.save(entity));
   }

   public Void deleteTarea(Long id) {
      if (!tareaRepository.existsById(id)) {
         throw new GlobalChallengeException("Tarea entity", ChallengeExceptionCode.NOT_FOUND);
      }
      tareaRepository.deleteById(id);
      return null;
   }

   public TareaDto getTareaById(Long id) {
      return TareaMapper.INSTANCE.toTareaDto(tareaRepository.findById(id)
            .orElseThrow(() -> new GlobalChallengeException("Tarea entity", ChallengeExceptionCode.NOT_FOUND)));
   }

   public List<TareaDto> getAllTareas() {
      return Optional.ofNullable(TareaMapper.INSTANCE.toTareaDto(tareaRepository.findAll()))
                     .filter(list -> !list.isEmpty())
                     .orElseThrow(() -> new GlobalChallengeException("Tareas", ChallengeExceptionCode.NOT_FOUND));
   }

   public List<TareaDto> getAllTareasByEstado(Long estadoId) {
      List<TareaDto> tareas = tareaRepository.findAll().stream()
                                                      .filter(tarea -> Objects.nonNull(tarea.getEstado()) && tarea.getEstado().getId().equals(estadoId))
                                                      .map(TareaMapper.INSTANCE::toTareaDto)
                                                      .collect(Collectors.toList());
      if (tareas.isEmpty()) {
         throw new GlobalChallengeException("Tareas", ChallengeExceptionCode.NOT_FOUND);
      }
      return tareas;
   }

   public List<TareaEntity> getAllTareasByUsuario(Long usuarioId) {
      return Optional.ofNullable(tareaRepository.findAllByUsuarioId(usuarioId))
                     .filter(list -> !list.isEmpty())
                     .orElseThrow(() -> new GlobalChallengeException("Tareas", ChallengeExceptionCode.NOT_FOUND));
   }

   @Transactional
   public TareaDto updateTareaEstado(Long tareaId, Long estado) {
      tareaRepository.updateTareaEstado(tareaId, estadoTareaRepository.findById(estado).orElseThrow(
            () -> new GlobalChallengeException("Estado Tarea", ChallengeExceptionCode.NOT_FOUND)
      ));
      return this.getTareaById(tareaId);
   }

   @Transactional
   public TareaDto updateTareaUsuario(Long tareaId, Long usuario) {
      tareaRepository.updateTareaUsuario(tareaId, usuarioRepository.findById(usuario).orElseThrow(
            () -> new GlobalChallengeException("Usuario", ChallengeExceptionCode.NOT_FOUND)
      ));
      return this.getTareaById(tareaId);
   }

   @Transactional
   public TareaDto updateTareaFinalizacion(Long tareaId, String fechaString) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      LocalDateTime finalizacion = LocalDateTime.parse(fechaString, formatter);
      tareaRepository.updateTareaFinalizacion(tareaId, finalizacion);
      return this.getTareaById(tareaId);
   }

}
