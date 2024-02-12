package com.move.challenge.utils;

import java.util.Arrays;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.move.challenge.entity.EstadoTareaEntity;
import com.move.challenge.entity.UsuarioEntity;
import com.move.challenge.repository.EstadoTareaRepository;
import com.move.challenge.repository.UsuarioRepository;

@Component
public class DataInitializer implements CommandLineRunner {

   private final UsuarioRepository usuarioRepository;

   private final EstadoTareaRepository estadoTareaRepository;

   @Autowired
   public DataInitializer(UsuarioRepository usuarioRepository, EstadoTareaRepository estadoTareaRepository) {
      this.usuarioRepository = usuarioRepository;
      this.estadoTareaRepository = estadoTareaRepository;
   }

   @Override
   public void run(String... args) throws Exception {
      // Genera algunos datos de usuario de prueba
      for (int i = 0; i < 5; i++) {
         UsuarioEntity user = new UsuarioEntity();
         user.setNombre("Usuario " + i);
         user.setEmail("email" + i + "@email.com");
         user.setClave(StringUtils.encodeHash("clave" + i));
         usuarioRepository.save(user);
      }

      Arrays.stream(EstadoTareaEnum.values()).forEach(estadoTareaEnum -> {
         EstadoTareaEntity estadoTareaEntity = new EstadoTareaEntity();
         estadoTareaEntity.setNombre(estadoTareaEnum.name());
         estadoTareaRepository.save(estadoTareaEntity);
      });

   }
}
