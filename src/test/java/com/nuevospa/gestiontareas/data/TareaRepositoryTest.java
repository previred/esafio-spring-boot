package com.nuevospa.gestiontareas.data;

import com.nuevospa.gestiontareas.model.tareas.EstadoTarea;
import com.nuevospa.gestiontareas.model.tareas.Tarea;
import com.nuevospa.gestiontareas.model.security.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class TareaRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private TareaRepository tareaRepository;

    private Usuario usuario;
    private EstadoTarea estadoTarea;

    @BeforeEach
    void setUp() {
        usuario = Usuario.builder()
                .email("usuario@example.com")
                .password("password")
                .nombre("Usuario Test")
                .build();
        estadoTarea = EstadoTarea.builder()
                .nombre("En Progreso")
                .build();
        usuario = entityManager.persistAndFlush(usuario);
        estadoTarea = entityManager.persistAndFlush(estadoTarea);
    }

    @Test
    void findByEstadoTareaNombre() {
        Tarea tarea = Tarea.builder()
                .descripcion("Tarea de prueba")
                .usuario(usuario)
                .estadoTarea(estadoTarea)
                .build();
        entityManager.persistAndFlush(tarea);

        List<Tarea> tareas = tareaRepository.findByEstadoTareaNombre("En Progreso");
        assertThat(tareas).isNotEmpty();
        assertThat(tareas.get(0).getDescripcion()).isEqualTo("Tarea de prueba");
        assertThat(tareas.get(0).getEstadoTarea().getNombre()).isEqualTo("En Progreso");
    }

    @Test
    void findByUsuarioId() {
        Tarea tarea = Tarea.builder()
                .descripcion("Otra tarea de prueba")
                .usuario(usuario)
                .estadoTarea(estadoTarea)
                .build();
        entityManager.persistAndFlush(tarea);

        List<Tarea> tareas = tareaRepository.findByUsuarioId(usuario.getId());
        assertThat(tareas).isNotEmpty();
        assertThat(tareas.get(0).getUsuario().getId()).isEqualTo(usuario.getId());
    }

    @Test
    void findById_WhenExists() {
        Tarea tarea = Tarea.builder()
                .descripcion("Tarea específica")
                .usuario(usuario)
                .estadoTarea(estadoTarea)
                .build();
        tarea = entityManager.persistFlushFind(tarea);

        Optional<Tarea> foundTarea = tareaRepository.findById(tarea.getId());
        assertThat(foundTarea.isPresent()).isTrue();
        assertThat(foundTarea.get().getDescripcion()).isEqualTo("Tarea específica");
    }

    @Test
    void findById_WhenNotExists() {
        Optional<Tarea> foundTarea = tareaRepository.findById(999L);
        assertThat(foundTarea.isPresent()).isFalse();
    }
}
