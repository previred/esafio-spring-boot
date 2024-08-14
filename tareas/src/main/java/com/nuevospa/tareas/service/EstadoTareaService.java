package com.nuevospa.tareas.service;

import com.nuevospa.tareas.entity.EstadoTareaEntity;
import com.nuevospa.tareas.model.EstadoTarea;
import com.nuevospa.tareas.repository.EstadoTareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoTareaService {

    @Autowired
    private EstadoTareaRepository estadoTareaRepository;

    // Listar todos los estados de tarea
    public List<EstadoTareaEntity> listarEstadosTarea() {
        return estadoTareaRepository.findAll();
    }

    // Crear un nuevo estado de tarea
    public EstadoTarea crearEstadoTarea(EstadoTarea estadoTarea) {
        estadoTareaRepository.save(EstadoTareaEntity.builder()
                .nombre(estadoTarea.getNombre())
                .build());
        return estadoTarea;
    }

    // Actualizar un estado de tarea
    public EstadoTareaEntity actualizarEstadoTarea(Long id, EstadoTarea estadoTareaActualizado) {
        Optional<EstadoTareaEntity> estadoTareaOptional = estadoTareaRepository.findById(id);
        if (estadoTareaOptional.isPresent()) {
            EstadoTareaEntity estadoTarea = estadoTareaOptional.get();
            estadoTarea.setNombre(estadoTareaActualizado.getNombre());
            return estadoTareaRepository.save(estadoTarea);
        } else {
            throw new RuntimeException("Estado de Tarea no encontrado");
        }
    }

    // Eliminar un estado de tarea
    public boolean eliminarEstadoTarea(Long id) {
        if(estadoTareaRepository.existsById(id)){
            estadoTareaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
