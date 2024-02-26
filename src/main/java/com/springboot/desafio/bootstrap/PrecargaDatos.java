package com.springboot.desafio.bootstrap;

import com.springboot.desafio.model.Authority;
import com.springboot.desafio.model.EstadoTarea;
import com.springboot.desafio.model.Tarea;
import com.springboot.desafio.model.User;
import com.springboot.desafio.repository.AuthorityRepository;
import com.springboot.desafio.repository.EstadoTareaRepository;
import com.springboot.desafio.repository.TareaRepository;
import com.springboot.desafio.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

@Component
public class PrecargaDatos implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EstadoTareaRepository estadoTareaRepository;

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        Authority authority = new Authority("ROLE_USER");
        authorityRepository.save(authority);

        Collection<Authority> authorities = List.of(
                authority
        );

        User user = new User( "admin", passwordEncoder.encode("123"), authorities);
        userRepository.save( user );

        EstadoTarea pendiente = new EstadoTarea("Pendiente");
        estadoTareaRepository.save(pendiente);
        EstadoTarea enCurso = new EstadoTarea("En curso");
        estadoTareaRepository.save(enCurso);
        EstadoTarea listo = new EstadoTarea("Listo");
        estadoTareaRepository.save(listo);

        tareaRepository.save(
                new Tarea("Tarea Uno",
                        "Esta es la primera tarea",
                        8, user,
                        pendiente)
        );
        tareaRepository.save(
                new Tarea("Tarea Dos",
                        "Esta es la segunda tarea",
                        13, user,
                        enCurso)
        );
        tareaRepository.save(
                new Tarea("Tarea Tres",
                        "Esta es la tercera tarea",
                        3, user,
                        listo)
        );

    }
}
