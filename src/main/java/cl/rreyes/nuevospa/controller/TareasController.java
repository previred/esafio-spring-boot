package cl.rreyes.nuevospa.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.rreyes.nuevospa.model.Tarea;
import cl.rreyes.nuevospa.service.TareasService;

@CrossOrigin
@RestController
@RequestMapping("/api/tareas")
public class TareasController {

	@Autowired
	private TareasService service;

	@GetMapping("/all")
	public List<Tarea> listar() {
		return service.getAllTareas();
	}

	@PostMapping
	public ResponseEntity<Tarea> crear(@RequestBody Tarea tarea) {
		tarea.setId(null);
		Tarea nuevaTarea = service.createTarea(tarea);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevaTarea);
	}

	@PutMapping("/{id}")
	public Tarea actualizar(@PathVariable Long id, @RequestBody Tarea tarea) {
		Tarea tareaActualizada = service.updateTarea(id, tarea);
		
		return tareaActualizada;
	}

	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Long id) {
		service.deleteTarea(id);
	}
}
