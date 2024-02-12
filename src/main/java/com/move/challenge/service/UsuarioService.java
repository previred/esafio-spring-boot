package com.move.challenge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.move.challenge.dto.UsuarioDto;
import com.move.challenge.dto.mapper.TareaMapper;
import com.move.challenge.dto.mapper.UsuarioMapper;
import com.move.challenge.entity.UsuarioEntity;
import com.move.challenge.repository.UsuarioRepository;
import com.move.challenge.utils.StringUtils;
import com.move.challenge.utils.exception.ChallengeExceptionCode;
import com.move.challenge.utils.exception.GlobalChallengeException;

@Service
public class UsuarioService {

   @Autowired
   private UsuarioRepository usuarioRepository;

   public UsuarioEntity getByEmailAndClave(String email, String clave) {
      return usuarioRepository.findByEmailAndClave(email, StringUtils.encodeHash(clave))
                              .orElseThrow(() -> new GlobalChallengeException(ChallengeExceptionCode.NOT_FOUND));
   }

   public UsuarioDto getUsuarioById(Long id) {
      return UsuarioMapper.INSTANCE.toUsuarioDto(usuarioRepository.findById(id)
                          .orElseThrow(() -> new GlobalChallengeException("Usuario entity", ChallengeExceptionCode.NOT_FOUND)));
   }

   public List<UsuarioDto> getAllUsuarios() {
      return Optional.ofNullable(UsuarioMapper.INSTANCE.toUsuarioDto(usuarioRepository.findAll()))
                     .filter(list -> !list.isEmpty())
                     .orElseThrow(() -> new GlobalChallengeException("Usuarios", ChallengeExceptionCode.NOT_FOUND));
   }

}
