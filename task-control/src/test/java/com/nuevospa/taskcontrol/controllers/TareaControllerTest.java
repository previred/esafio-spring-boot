package com.nuevospa.taskcontrol.controllers;

import com.nuevospa.taskcontrol.dtos.entities.EstadoTarea;
import com.nuevospa.taskcontrol.dtos.entities.HistorialEstado;
import com.nuevospa.taskcontrol.dtos.entities.Tarea;
import com.nuevospa.taskcontrol.dtos.entities.Usuario;
import com.nuevospa.taskcontrol.dtos.requests.AddTareaRequest;
import com.nuevospa.taskcontrol.dtos.requests.DeleteTareaRequest;
import com.nuevospa.taskcontrol.dtos.requests.UpdateTareaRequest;
import com.nuevospa.taskcontrol.dtos.responses.TareaResponse;
import com.nuevospa.taskcontrol.services.TareaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TareaControllerTest {

    @InjectMocks
    private TareaController tareaController;

    @Mock
    private TareaService tareaService;

    @Test
    public void addTask_shouldAddTaskWithHttpStatusOk() {
        //Arrange
        AddTareaRequest mockedRequest = mockRequest();
        TareaResponse mockedResponse = mockResponse();
        //Act
        when(tareaService.addTarea(mockedRequest)).thenReturn(mockedResponse);
        ResponseEntity<TareaResponse> response = tareaController.addTask(mockedRequest);
        //Assert
        assertNotNull(response);
        assertTrue(HttpStatus.OK.equals(response.getStatusCode()));
    }

    @Test
    public void deleteTask_shouldDeleteTaskWithHttpStatusOk() {
        //Arrange
        DeleteTareaRequest mockedRequest = new DeleteTareaRequest();
        mockedRequest.setIdTarea(1);
        TareaResponse mockedResponse = new TareaResponse();
        mockedResponse.setId(1);
        mockedResponse.setNombre("Revisar impresora");
        mockedResponse.setDescripcion("Revisar si la impresora posee algún desperfecto físico");
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setNombre("Pedro Soto");
        usuario.setClave("a2asfGfdfdf4");
        mockedResponse.setUsuario(usuario);
        Tarea tarea = new Tarea();
        tarea.setIdTarea(1);
        tarea.setNombre("Revisar impresora");
        tarea.setDescripcion("Revisar si la impresora posee algún desperfecto físico");
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
        //Act
        when(tareaService.deleteTarea(mockedRequest)).thenReturn(mockedResponse);
        ResponseEntity<TareaResponse> response = tareaController.deleteTask(mockedRequest);
        //Assert
        assertNotNull(response);
        assertTrue(HttpStatus.OK.equals(response.getStatusCode()));
    }

    @Test
    public void readTask_shouldReadTaskWithHttpStatusOk() {
        //Arrange
        TareaResponse mockedResponse = new TareaResponse();
        mockedResponse.setId(1);
        mockedResponse.setNombre("Revisar impresora");
        mockedResponse.setDescripcion("Revisar si la impresora posee algún desperfecto físico");
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setNombre("Pedro Soto");
        usuario.setClave("a2asfGfdfdf4");
        mockedResponse.setUsuario(usuario);
        Tarea tarea = new Tarea();
        tarea.setIdTarea(1);
        tarea.setNombre("Revisar impresora");
        tarea.setDescripcion("Revisar si la impresora posee algún desperfecto físico");
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
        //Act
        when(tareaService.readTarea(anyLong())).thenReturn(mockedResponse);
        ResponseEntity<TareaResponse> response = tareaController.readTask(1);
        //Assert
        assertNotNull(response);
        assertTrue(HttpStatus.OK.equals(response.getStatusCode()));
    }

    @Test
    public void updateTask_shouldUpdateTaskWithHttpStatusOk() {
        //Arrange
        UpdateTareaRequest mockedRequest = new UpdateTareaRequest();
        mockedRequest.setIdEstadoTarea(1);
        mockedRequest.setNombre("Tarea 1");
        mockedRequest.setDescripcion("Esta es la tarea 1");
        mockedRequest.setIdUsuario(2);
        mockedRequest.setIdEstadoTarea(2);
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
        TareaResponse mockedResponse = new TareaResponse();
        mockedResponse.setId(1);
        mockedResponse.setNombre("Tarea 1");
        mockedResponse.setDescripcion("Esta es la tarea 1");
        mockedResponse.setUsuario(usuario);
        mockedResponse.setHistorialEstados(tarea.getHistorialEstados());
        //Act
        when(tareaService.updateTarea(mockedRequest)).thenReturn(mockedResponse);
        ResponseEntity<TareaResponse> response = tareaController.updateTask(mockedRequest);
        //Assert
        assertNotNull(mockedResponse);
        assertTrue(HttpStatus.OK.equals(response.getStatusCode()));
    }

    private AddTareaRequest mockRequest() {
        AddTareaRequest mockedRequest = new AddTareaRequest();
        mockedRequest.setNombre("Revisar impresora");
        mockedRequest.setDescripcion("Revisar si la impresora posee algún desperfecto físico");
        mockedRequest.setIdUsuario(1);
        return mockedRequest;
    }

    private TareaResponse mockResponse() {
        TareaResponse mockedResponse = new TareaResponse();
        mockedResponse.setId(1);
        mockedResponse.setNombre("Revisar impresora");
        mockedResponse.setDescripcion("Revisar si la impresora posee algún desperfecto físico");
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setNombre("Pedro Soto");
        usuario.setClave("a2asfGfdfdf4");
        mockedResponse.setUsuario(usuario);
        return mockedResponse;
    }
}
