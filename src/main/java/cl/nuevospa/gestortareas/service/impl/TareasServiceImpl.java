package cl.nuevospa.gestortareas.service.impl;

import cl.nuevospa.gestortareas.model.Tareas;
import cl.nuevospa.gestortareas.repository.TareaRepository;
import cl.nuevospa.gestortareas.repository.TareasEstadoRepository;
import cl.nuevospa.gestortareas.service.TareasService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TareasServiceImpl implements TareasService {
    private final TareaRepository tareaRepository;

    private final TareasEstadoRepository estadoRepository;

    public TareasServiceImpl(TareaRepository tareaRepository, TareasEstadoRepository estadoRepository) {
        this.tareaRepository = tareaRepository;
        this.estadoRepository = estadoRepository;
    }

    @Override
    public List<Tareas> findAllTareas() {
        return tareaRepository.findAll();
    }

    @Override
    public Optional<Tareas> findTareaById(Integer id) {
        return tareaRepository.findById(id);
    }

    @Override
    public List<Tareas> findTareasByEstado(Integer idEstado) {
        return tareaRepository.findAllByEstado(estadoRepository.findById(idEstado).orElse(null));
    }

    @Override
    public Void addTarea(Tareas tareas) {
        tareas.setFechaCreacion(OffsetDateTime.now());
        tareas.setFechaActualizacion(OffsetDateTime.now());
        tareaRepository.save(tareas);
        return null;
    }

    @Override
    public Void updateTarea(Integer id, Tareas tareas) {
        Optional<Tareas> optionalTareas = tareaRepository.findById(id);
        if (optionalTareas.isPresent()) {
            Tareas tareaActual = optionalTareas.get();
            tareaActual.setTitulo(tareas.getTitulo());
            tareaActual.setDescripcion(tareas.getDescripcion());
            tareaActual.setEstado(tareas.getEstado());
            tareaActual.setFechaActualizacion(OffsetDateTime.now());
            tareaRepository.save(tareaActual);
        } else {
            throw new EntityNotFoundException("Usuario no encontrado con ID: " + id);
        }
        return null;
    }

    @Override
    public Void deleteTarea(Integer id) {
        tareaRepository.deleteById(id);
        return null;
    }
}
