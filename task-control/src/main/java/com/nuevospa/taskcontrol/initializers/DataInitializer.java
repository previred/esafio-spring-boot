package com.nuevospa.taskcontrol.initializers;

import com.nuevospa.taskcontrol.dtos.entities.EstadoTarea;
import com.nuevospa.taskcontrol.dtos.entities.Usuario;
import com.nuevospa.taskcontrol.repositories.EstadoTareaRepository;
import com.nuevospa.taskcontrol.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    private final UsuarioRepository usuarioRepository;

    private final EstadoTareaRepository estadoTareaRepository;

    @Autowired
    public DataInitializer(UsuarioRepository usuarioRepository, EstadoTareaRepository estadoTareaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.estadoTareaRepository = estadoTareaRepository;
    }

    @PostConstruct
    public void prepareData() {
        Usuario usuario1 = new Usuario();
        usuario1.setNombre("Pedro García");
        usuario1.setClave("a2asfGfdfdf4");
        Usuario usuario2 = new Usuario();
        usuario2.setNombre("Pepe Grillo");
        usuario2.setClave("a2asfGfdfdf3");
        this.usuarioRepository.save(usuario1);
        this.usuarioRepository.save(usuario2);
        EstadoTarea estadoTarea1 = new EstadoTarea();
        estadoTarea1.setEstado("Por hacer");
        estadoTarea1.setDescripcion("Indica que el desarrollo de la tarea no ha comenzado aún");
        EstadoTarea estadoTarea2 = new EstadoTarea();
        estadoTarea2.setEstado("En desarrollo");
        estadoTarea2.setDescripcion("Indica que la tarea se encuentra en desarrollo");
        EstadoTarea estadoTarea3 = new EstadoTarea();
        estadoTarea3.setEstado("Terminada");
        estadoTarea3.setDescripcion("Indica que el desarrollo de la tarea finalizó");
        this.estadoTareaRepository.save(estadoTarea1);
        this.estadoTareaRepository.save(estadoTarea2);
        this.estadoTareaRepository.save(estadoTarea3);
    }
}
