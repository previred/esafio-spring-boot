package cl.rreyes.nuevospa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.rreyes.nuevospa.model.EstadoTarea;
import cl.rreyes.nuevospa.model.Tarea;
import cl.rreyes.nuevospa.model.Usuario;
import cl.rreyes.nuevospa.repository.EstadoTareaRepository;
import cl.rreyes.nuevospa.repository.TareaRepository;
import cl.rreyes.nuevospa.repository.UsuarioRepository;

@Service
public class TareasService {

	@Autowired
	private TareaRepository tareaRepository;
	
	@Autowired
    private EstadoTareaRepository estadoTareaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

	// Crear una nueva tarea
	
	 public Tarea createTarea(Tarea tarea) {
		 // Asignar estadoTarea predeterminado (id = 1)
	        EstadoTarea estadoTarea = estadoTareaRepository.findById(1L)
	                .orElseThrow(() -> new IllegalArgumentException("Estado no encontrado"));
	        tarea.setEstadoTarea(estadoTarea);

	        // Asignar el usuario por ID
	        Usuario usuario = usuarioRepository.findById((long)tarea.getUsuarioId())
	                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
	        tarea.setUsuarioId(usuario.getId());

	        return tareaRepository.save(tarea);
	    }
	 /*
	public Tarea createTarea(Tarea tarea) {
		return tareaRepository.save(tarea);
	}
*/
	// Obtener todas las tareas
	public List<Tarea> getAllTareas() {
		return tareaRepository.findAll();
	}

	// Obtener tarea por id
	public Optional<Tarea> getTareaById(long id) {
		return tareaRepository.findById(id);
	}

	// Actualizar tarea existente
	public Tarea updateTarea(long id, Tarea updatedTarea) {
		Optional<Tarea> existingTarea = tareaRepository.findById(id);
		if (existingTarea.isPresent()) {
			Tarea tarea = existingTarea.get();
			tarea.setTitulo(updatedTarea.getTitulo());
			tarea.setDescripcion(updatedTarea.getDescripcion());
			tarea.setFechaVencimiento(updatedTarea.getFechaVencimiento());
			tarea.setUsuarioId(updatedTarea.getUsuarioId());
			tarea.setEstadoTarea(updatedTarea.getEstadoTarea());
			return tareaRepository.save(tarea);
		}
		return null;
	}

	// Eliminar tarea por id
	public void deleteTarea(long id) {
		tareaRepository.deleteById(id);
	}
}
