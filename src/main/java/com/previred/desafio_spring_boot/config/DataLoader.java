package com.previred.desafio_spring_boot.config;

import com.previred.desafio_spring_boot.Domain.EstadoTarea;
import com.previred.desafio_spring_boot.Domain.Usuario;
import com.previred.desafio_spring_boot.repository.EstadoTareaRepository;
import com.previred.desafio_spring_boot.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner initData(EstadoTareaRepository estadoTareaRepository, UsuarioRepository usuarioRepository) {
        return args -> {
            EstadoTarea estadoIniciado = new EstadoTarea();
            estadoIniciado.setNombre("Iniciada");
            estadoTareaRepository.save(estadoIniciado);

            EstadoTarea estadoEnProceso = new EstadoTarea();
            estadoEnProceso.setNombre("En proceso");
            estadoTareaRepository.save(estadoEnProceso);

            EstadoTarea estadoEnTerminada = new EstadoTarea();
            estadoEnTerminada.setNombre("terminada");
            estadoTareaRepository.save(estadoEnTerminada);

            Usuario usuario = new Usuario();
            usuario.setUsername("UsuarioPrueba");
            usuario.setPassword("123456");
            usuario.setRole("ADMN");

            usuarioRepository.save(usuario);

        };
    }
}

