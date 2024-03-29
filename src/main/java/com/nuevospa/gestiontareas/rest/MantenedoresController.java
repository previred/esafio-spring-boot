package com.nuevospa.gestiontareas.rest;

import com.nuevospa.gestiontareas.dto.tareas.EstadoTareaDTO;
import com.nuevospa.gestiontareas.service.MantenedoresService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/v1")
public class MantenedoresController {
    private final MantenedoresService mantenedoresService;

    public MantenedoresController(MantenedoresService mantenedoresService) {
        this.mantenedoresService = mantenedoresService;
    }

    @GetMapping("/estados_tareas")
    public ResponseEntity<Set<EstadoTareaDTO>> obtenerTodosLosEstadosTareas() {
        return ResponseEntity.ok(mantenedoresService.obtenerTodosLosEstadosTareas());
    }
}
