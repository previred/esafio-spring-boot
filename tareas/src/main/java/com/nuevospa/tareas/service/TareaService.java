package com.nuevospa.tareas.service;

import com.nuevospa.tareas.entity.EstadoTareaEntity;
import com.nuevospa.tareas.entity.TareaEntity;
import com.nuevospa.tareas.model.Tarea;
import com.nuevospa.tareas.repository.EstadoTareaRepository;
import com.nuevospa.tareas.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private EstadoTareaRepository estadoTareaRepository;

    // Listar todas las tareas
    public List<TareaEntity> listarTareas() {
        return tareaRepository.findAll();
    }

    // Crear una nueva tarea
    public Tarea crearTarea(Tarea tarea) {
        EstadoTareaEntity estadoTareaEntity = estadoTareaRepository.findById(tarea.getEstadoId())
                .orElseThrow(() -> new RuntimeException("Estado de tarea no encontrado"));

        tareaRepository.save(TareaEntity.builder()
                .descripcion(tarea.getDescripcion())
                .estadoTarea(estadoTareaEntity)
                .build());

        return tarea;
    }

    // Actualizar una tarea
    public TareaEntity actualizarTarea(Long id, Tarea tareaActualizada) {
        EstadoTareaEntity estadoTareaEntity = estadoTareaRepository.findById(tareaActualizada.getEstadoId())
                .orElseThrow(() -> new RuntimeException("Estado de tarea no encontrado"));

        Optional<TareaEntity> tareaOptional = tareaRepository.findById(id);
        if (tareaOptional.isPresent()) {
            TareaEntity tarea = tareaOptional.get();
            tarea.setDescripcion(tareaActualizada.getDescripcion());
            tarea.setEstadoTarea(estadoTareaEntity);
            return tareaRepository.save(tarea);
        } else {
            throw new RuntimeException("Tarea no encontrada");
        }
    }

    // Eliminar una tarea
    public boolean eliminarTarea(Long id) {
        if(tareaRepository.existsById(id)){
            tareaRepository.deleteById(id);
            return true;
        }

        return false;

    }
}
