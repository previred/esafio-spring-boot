package com.nuevospa.taskcontrol.services.impl;

import com.nuevospa.taskcontrol.dtos.entities.EstadoTarea;
import com.nuevospa.taskcontrol.dtos.entities.HistorialEstado;
import com.nuevospa.taskcontrol.dtos.entities.Tarea;
import com.nuevospa.taskcontrol.dtos.entities.Usuario;
import com.nuevospa.taskcontrol.dtos.requests.AddTareaRequest;
import com.nuevospa.taskcontrol.dtos.requests.DeleteTareaRequest;
import com.nuevospa.taskcontrol.dtos.requests.UpdateTareaRequest;
import com.nuevospa.taskcontrol.dtos.responses.TareaResponse;
import com.nuevospa.taskcontrol.repositories.EstadoTareaRepository;
import com.nuevospa.taskcontrol.repositories.TareaRepository;
import com.nuevospa.taskcontrol.repositories.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TareaServiceImplTest {

    @InjectMocks
    private TareaServiceImpl tareaService;

    @Mock
    private TareaRepository tareaRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private EstadoTareaRepository estadoTareaRepository;

    @Test
    public void addTask_ShouldAddTaskToDatabase() {
        //Arrange
        AddTareaRequest request = new AddTareaRequest();
        request.setNombre("Revisar impresora");
        request.setDescripcion("Revisar si la impresora posee algún desperfecto físico");
        request.setIdUsuario(1);
        Usuario usuario = mockUser();
        Tarea tarea = mockTask();
        tarea.setUsuario(usuario);
        EstadoTarea estadoTarea = mockTaskStatus();
        HistorialEstado historialEstado = mockStatusHistory(tarea, estadoTarea);
        tarea.addHistorialEstado(historialEstado);
        //Act
        when(usuarioRepository.findById(request.getIdUsuario()))
                .thenReturn(Optional.of(usuario));
        when(estadoTareaRepository.findById(anyLong())).thenReturn(Optional.of(estadoTarea));
        when(tareaRepository.save(any(Tarea.class))).thenReturn(tarea);
        TareaResponse tareaResponse = tareaService.addTarea(request);
        //Assert
        assertNotNull(tareaResponse);
    }

    @Test
    public void deleteTask_shouldDeleteTaskFromDatabase() {
        //Arrange
        DeleteTareaRequest request = new DeleteTareaRequest();
        request.setIdTarea(1);
        Tarea tarea = mockTask();
        Usuario usuario = mockUser();
        tarea.setUsuario(usuario);
        EstadoTarea estadoTarea = new EstadoTarea();
        estadoTarea.setIdEstadoTarea(1);
        estadoTarea.setEstado("Por hacer");
        estadoTarea.setDescripcion("Indica que el desarrollo de la tarea no ha comenzado aún");
        HistorialEstado historialEstado = new HistorialEstado();
        historialEstado.setTarea(tarea);
        historialEstado.setEstadoTarea(estadoTarea);
        historialEstado.setFechaInicioEstado(LocalDateTime.now());
        tarea.addHistorialEstado(historialEstado);
        estadoTarea.addHistorialEstado(historialEstado);
        //Act
        when(tareaRepository.findById(anyLong())).thenReturn(Optional.of(tarea));
        doNothing().when(tareaRepository).delete(tarea);
        tareaRepository.delete(tarea);
        TareaResponse response = tareaService.deleteTarea(request);
        //Assert
        assertNotNull(response);
    }

    @Test
    public void readTask_shouldReadTaskFromDatabase() {
        //Arrange
        Tarea tarea = mockTask();
        Usuario usuario = mockUser();
        tarea.setUsuario(usuario);
        EstadoTarea estadoTarea = new EstadoTarea();
        estadoTarea.setIdEstadoTarea(1);
        estadoTarea.setEstado("Por hacer");
        estadoTarea.setDescripcion("Indica que el desarrollo de la tarea no ha comenzado aún");
        HistorialEstado historialEstado = new HistorialEstado();
        historialEstado.setTarea(tarea);
        historialEstado.setEstadoTarea(estadoTarea);
        historialEstado.setFechaInicioEstado(LocalDateTime.now());
        tarea.addHistorialEstado(historialEstado);
        estadoTarea.addHistorialEstado(historialEstado);
        //Act
        when(tareaRepository.findById(anyLong())).thenReturn(Optional.of(tarea));
        TareaResponse response = tareaService.readTarea(1);
        //Assert
        assertNotNull(response);
    }

    @Test
    public void updateTask_shouldUpdateTaskFromDatabase() {
        //Arrange
        Tarea tarea = new Tarea();
        tarea.setNombre("Tarea 1");
        tarea.setDescripcion("Esta es la tarea 1");
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(2);
        usuario.setNombre("Pepe Grillo");
        usuario.setClave("testpass");
        tarea.setUsuario(usuario);
        EstadoTarea estadoAnterior = new EstadoTarea();
        estadoAnterior.setIdEstadoTarea(1);
        estadoAnterior.setEstado("Por hacer");
        estadoAnterior.setDescripcion("Indica que el desarrollo de la tarea aún no ha comenzado");
        EstadoTarea estadoActualizado = new EstadoTarea();
        estadoActualizado.setIdEstadoTarea(2);
        estadoActualizado.setEstado("En desarrollo");
        estadoActualizado.setDescripcion("Indica que la tarea se encuentra en desarrollo");
        HistorialEstado historialEstadoAnterior = new HistorialEstado();
        historialEstadoAnterior.setTarea(tarea);
        historialEstadoAnterior.setEstadoTarea(estadoAnterior);
        historialEstadoAnterior.setFechaInicioEstado(LocalDateTime.now());
        HistorialEstado historialEstadoActual = new HistorialEstado();
        historialEstadoActual.setTarea(tarea);
        historialEstadoActual.setEstadoTarea(estadoActualizado);
        historialEstadoActual.setFechaInicioEstado(LocalDateTime.now());
        tarea.addHistorialEstado(historialEstadoAnterior);
        tarea.addHistorialEstado(historialEstadoActual);
        UpdateTareaRequest mockedRequest = new UpdateTareaRequest();
        mockedRequest.setIdEstadoTarea(1);
        mockedRequest.setNombre("Tarea 1");
        mockedRequest.setDescripcion("Esta es la tarea 1");
        mockedRequest.setIdUsuario(2);
        mockedRequest.setIdEstadoTarea(2);
        //Act
        when(tareaRepository.findById(anyLong())).thenReturn(Optional.of(tarea));
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));
        when(estadoTareaRepository.findById(anyLong())).thenReturn(Optional.of(estadoActualizado));
        when(tareaRepository.save(any(Tarea.class))).thenReturn(tarea);
        TareaResponse response = tareaService.updateTarea(mockedRequest);
        //Assert
        assertNotNull(response);
    }

    private Usuario mockUser() {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setNombre("Pedro Soto");
        usuario.setClave("a2asfGfdfdf4");
        return usuario;
    }

    private Tarea mockTask() {
        Tarea tarea = new Tarea();
        tarea.setIdTarea(1);
        tarea.setNombre("Revisar impresora");
        tarea.setDescripcion("Revisar si la impresora posee algún desperfecto físico");
        return tarea;
    }

    private EstadoTarea mockTaskStatus() {
        EstadoTarea estadoTarea = new EstadoTarea();
        estadoTarea.setIdEstadoTarea(1);
        estadoTarea.setEstado("Por hacer");
        estadoTarea.setDescripcion("Indica que el desarrollo de la tarea no ha comenzado aún");
        return estadoTarea;
    }

    private HistorialEstado mockStatusHistory(Tarea tarea, EstadoTarea estadoTarea) {
        HistorialEstado historialEstado = new HistorialEstado();
        historialEstado.setTarea(tarea);
        historialEstado.setEstadoTarea(estadoTarea);
        historialEstado.setFechaInicioEstado(LocalDateTime.now());
        return historialEstado;
    }


}
