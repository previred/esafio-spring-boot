package service;

import model.Tarea;
import repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TareaService {
    @Autowired
    private TareaRepository tareaRepository;

    public List<Tarea> getAllTareas() {
        return tareaRepository.findAll();
    }

    public Tarea saveTarea(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    public Tarea updateTarea(Long id, Tarea tarea) {
        Optional<Tarea> existingTarea = tareaRepository.findById(id);
        if (existingTarea.isPresent()) {
            Tarea updatedTarea = existingTarea.get();
            updatedTarea.setTitulo(tarea.getTitulo());
            updatedTarea.setDescripcion(tarea.getDescripcion());
            updatedTarea.setFechaCreacion(tarea.getFechaCreacion());
            updatedTarea.setEstado(tarea.getEstado());
            return tareaRepository.save(updatedTarea);
        } else {
            return null; // Handle not found case
        }
    }

    public void deleteTarea(Long id) {
        tareaRepository.deleteById(id);
    }
}