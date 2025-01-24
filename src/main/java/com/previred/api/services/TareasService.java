package com.previred.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.previred.api.dtos.TareaDTO;
import com.previred.api.dtos.TareaResponseDTO;
import com.previred.api.models.Tareas;
import com.previred.api.repositories.TareasRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TareasService {

    private final TareasRepository tareasRepository;
    private final EstadosTareaService estadosTareaService;
    private final UsuariosService usuariosService;
    private final ObjectMapper objectMapper;

    public TareaDTO crearTarea(TareaDTO tareaIn) {
        Tareas tarea = tareasRepository
                .saveAndFlush(objectMapper.convertValue(tareaIn, Tareas.class));
        return objectMapper.convertValue(tarea, TareaDTO.class);
    }

    public TareaDTO actualizarTarea(TareaDTO tareaIn) {
        Optional<Tareas> getTarea = tareasRepository.findById(tareaIn.getId());
        if (getTarea.isPresent()) {
            Tareas tarea = tareasRepository
                    .saveAndFlush(objectMapper.convertValue(tareaIn, Tareas.class));
            return objectMapper.convertValue(tarea, TareaDTO.class);
        }
        return null;
    }

    public ResponseEntity<Void> eliminarTarea(Long tareaId) {
        Optional<Tareas> getTarea = tareasRepository.findById(tareaId);
        if (getTarea.isPresent()) {
            tareasRepository.deleteById(tareaId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public TareaResponseDTO buscarPorId(Long tareaId) {
        Optional<Tareas> tarea = tareasRepository.findById(tareaId);
        if (tarea.isPresent()) {
            return mapTareaToDTO(tarea.get());
        }
        return null;
    }

    public List<TareaResponseDTO> listarTareas() {
        List<Tareas> listaTareas = tareasRepository.findAll();
        if (!listaTareas.isEmpty()) {
            return listaTareas.stream()
                    .map(this::mapTareaToDTO)
                    .toList();
        }
        return new ArrayList<>();
    }

    private TareaResponseDTO mapTareaToDTO(Tareas tarea) {
        TareaResponseDTO tareaResponse = new TareaResponseDTO();
        tareaResponse.setId(tarea.getId());
        tareaResponse.setTarea(tarea.getTarea());
        tareaResponse.setEstado(estadosTareaService.buscarPorId(tarea.getEstadoId()));
        tareaResponse.setUsuario(usuariosService.buscarPorId(tarea.getUsuarioId()));
        return tareaResponse;
    }

}
