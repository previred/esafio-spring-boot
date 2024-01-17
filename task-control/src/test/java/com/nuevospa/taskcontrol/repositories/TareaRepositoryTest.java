package com.nuevospa.taskcontrol.repositories;

import com.nuevospa.taskcontrol.dtos.entities.EstadoTarea;
import com.nuevospa.taskcontrol.dtos.entities.HistorialEstado;
import com.nuevospa.taskcontrol.dtos.entities.Tarea;
import com.nuevospa.taskcontrol.dtos.entities.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class TareaRepositoryTest {

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void saveTasks_shouldSaveTasks() {
        //Arrange
        Usuario usuario = new Usuario();
        usuario.setNombre("Pedro García");
        usuario.setClave("a2asfGfdfdf4");
        Tarea tarea = new Tarea();
        tarea.setNombre("Revisar impresora");
        tarea.setDescripcion("Revisar si la impresora posee algún desperfecto físico");
        tarea.setUsuario(usuario);
        usuario.addTarea(tarea);
        usuarioRepository.save(usuario);
        EstadoTarea estadoTarea1 = new EstadoTarea();
        estadoTarea1.setEstado("Por hacer");
        estadoTarea1.setDescripcion("Indica que el desarrollo de la tarea aún no inicia");
        EstadoTarea estadoTarea2 = new EstadoTarea();
        estadoTarea2.setEstado("Activa");
        estadoTarea2.setDescripcion("Indica que el desarrollo de la tarea está en curso");
        HistorialEstado historial1 = new HistorialEstado();
        historial1.setTarea(tarea);
        historial1.setEstadoTarea(estadoTarea1);
        historial1.setFechaInicioEstado(LocalDateTime.of(2024,1,12,12,10));
        historial1.setFechaFinEstado(LocalDateTime.of(2024,1,12,12,40));
        HistorialEstado historial2 = new HistorialEstado();
        historial1.setTarea(tarea);
        historial1.setEstadoTarea(estadoTarea2);
        historial2.setFechaInicioEstado(LocalDateTime.of(2024,1,12,12,40));
        tarea.addHistorialEstado(historial1);
        tarea.addHistorialEstado(historial2);
        estadoTarea1.addHistorialEstado(historial1);
        estadoTarea2.addHistorialEstado(historial2);
        //Act
        Tarea savedTask = tareaRepository.save(tarea);
        //Assert
        assertNotNull(savedTask);
        assertEquals(2, savedTask.getHistorialEstados().size());
    }

}
