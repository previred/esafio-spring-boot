package com.nuevospa.tareas.configuration;

import com.nuevospa.tareas.entity.EstadoTareaEntity;
import com.nuevospa.tareas.entity.TareaEntity;
import com.nuevospa.tareas.entity.UsuarioEntity;
import com.nuevospa.tareas.repository.EstadoTareaRepository;
import com.nuevospa.tareas.repository.TareaRepository;
import com.nuevospa.tareas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoader {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private EstadoTareaRepository estadoTareaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner loadData() {
        return args -> {
            usuarioRepository.save(UsuarioEntity.builder()
                    .nombre("Juan Pérez")
                    .email("juan.perez@example.com")
                    .password(passwordEncoder.encode("123456"))
                    .build());

            usuarioRepository.save(UsuarioEntity.builder()
                    .nombre("Ana García")
                    .email("ana.garcia@example.com")
                    .password(passwordEncoder.encode("123456"))
                    .build());

            usuarioRepository.save(UsuarioEntity.builder()
                    .nombre("Luis Fernández")
                    .email("luis.fernandez@example.com")
                    .password(passwordEncoder.encode("123456"))
                    .build());

            estadoTareaRepository.save(EstadoTareaEntity.builder()
                    .nombre("Abierta")
                    .build());

            estadoTareaRepository.save(EstadoTareaEntity.builder()
                    .nombre("Pendiente")
                    .build());

            estadoTareaRepository.save(EstadoTareaEntity.builder()
                    .nombre("Cerrada")
                    .build());

            tareaRepository.save(TareaEntity.builder()
                    .descripcion("Descripción de la tarea 1")
                    .estadoTarea(estadoTareaRepository.findById(1L).isPresent() ? estadoTareaRepository.findById(1L).get() : null)
                    .build());

            tareaRepository.save(TareaEntity.builder()
                    .descripcion("Descripción de la tarea 2")
                    .estadoTarea(estadoTareaRepository.findById(2L).isPresent() ? estadoTareaRepository.findById(2L).get() : null)
                    .build());

            tareaRepository.save(TareaEntity.builder()
                    .descripcion("Descripción de la tarea 3")
                    .estadoTarea(estadoTareaRepository.findById(3L).isPresent() ? estadoTareaRepository.findById(3L).get() : null)
                    .build());
        };
    }
}
