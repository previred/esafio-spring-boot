package com.nuevospa.gestiontareas.service.impl;

import com.nuevospa.gestiontareas.data.EstadosTareasRepository;
import com.nuevospa.gestiontareas.dto.tareas.EstadoTareaDTO;
import com.nuevospa.gestiontareas.model.tareas.EstadoTarea;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MantenedoresServiceImplTest {
    @Mock
    private EstadosTareasRepository estadosTareasRepository;
    @InjectMocks
    private MantenedoresServiceImpl mantenedoresService;

    @Test
    void obtenerTodosLosEstadosTareasTest() {
        Set<EstadoTarea> estados = new HashSet<>();
        estados.add(EstadoTarea.builder().id(1L).nombre("Creada").build());
        estados.add(EstadoTarea.builder().id(2L).nombre("Anulada").build());

        given(estadosTareasRepository.findAll()).willReturn(new ArrayList<>(estados));

        Set<EstadoTareaDTO> dtos = mantenedoresService.obtenerTodosLosEstadosTareas();

        assertNotNull(dtos);
        assertEquals(estados.size(), dtos.size());
    }
}
