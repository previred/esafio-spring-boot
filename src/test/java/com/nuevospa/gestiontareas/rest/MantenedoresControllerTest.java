package com.nuevospa.gestiontareas.rest;

import com.nuevospa.gestiontareas.dto.tareas.EstadoTareaDTO;
import com.nuevospa.gestiontareas.service.MantenedoresService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MantenedoresControllerTest {
    @Mock
    private MantenedoresService mantenedoresService;
    @InjectMocks
    private MantenedoresController mantenedoresController;

    @Test
    void obtenerTodosLosEstadosTareas_DebeRetornarEstados_WhenHayEstadosDisponibles() {
        // Preparar
        EstadoTareaDTO estadoTareaDTO = new EstadoTareaDTO();
        Set<EstadoTareaDTO> expectedEstados = new HashSet<>();
        expectedEstados.add(estadoTareaDTO);
        when(mantenedoresService.obtenerTodosLosEstadosTareas()).thenReturn(expectedEstados);

        // Ejecutar
        ResponseEntity<Set<EstadoTareaDTO>> response = mantenedoresController.obtenerTodosLosEstadosTareas();

        // Verificar
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedEstados, response.getBody());
    }

    @Test
    void obtenerTodosLosEstadosTareas_DebeRetornarColeccionVacia_WhenNoHayEstadosDisponibles() {
        // Preparar
        Set<EstadoTareaDTO> expectedEstados = Collections.emptySet();
        when(mantenedoresService.obtenerTodosLosEstadosTareas()).thenReturn(expectedEstados);

        // Ejecutar
        ResponseEntity<Set<EstadoTareaDTO>> response = mantenedoresController.obtenerTodosLosEstadosTareas();

        // Verificar
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedEstados, response.getBody());
        assertTrue(response.getBody().isEmpty());
    }
}
