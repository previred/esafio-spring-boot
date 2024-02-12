package com.move.challenge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.move.challenge.dto.EstadoTareaDto;
import com.move.challenge.dto.TareaDto;
import com.move.challenge.dto.mapper.EstadoTareaMapper;
import com.move.challenge.dto.mapper.TareaMapper;
import com.move.challenge.dto.mapper.UsuarioMapper;
import com.move.challenge.entity.EstadoTareaEntity;
import com.move.challenge.repository.EstadoTareaRepository;
import com.move.challenge.utils.exception.ChallengeExceptionCode;
import com.move.challenge.utils.exception.GlobalChallengeException;
import com.move.challenge.utils.exception.InvalidCredentialsException;

@Service
public class EstadoTareaService {

   @Autowired
   private EstadoTareaRepository estadoTareaRepository;


   public List<EstadoTareaDto> getAllEstadosTarea() {
      return Optional.ofNullable(EstadoTareaMapper.INSTANCE.toEstadoTareaDto(estadoTareaRepository.findAll()))
                     .filter(list -> !list.isEmpty())
                     .orElseThrow(() -> new GlobalChallengeException("Estados tarea", ChallengeExceptionCode.NOT_FOUND));
   }



}
