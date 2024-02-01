package com.desafio.spring.desafiospringboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.desafio.spring.desafiospringboot.dao.EstadoTareaRepository;
import com.desafio.spring.desafiospringboot.dao.TareaRepository;
import com.desafio.spring.desafiospringboot.model.EstadoTarea;
import com.desafio.spring.desafiospringboot.model.Tarea;

@Service
public class TareaService {
	
	@Autowired
	private TareaRepository tareaRepository;
	
	@Autowired
	private EstadoTareaRepository estadoTareaRepository;
	
	public ResponseEntity<?> guardarTarea(Tarea tarea) {
		try {
			Tarea tareaAux = tareaRepository.findOneByNombreTarea(tarea.getNombreTarea());
			if(tareaAux != null) {
				return new ResponseEntity<String>("La tarea ya existe!", HttpStatus.OK);
			}
			Optional<EstadoTarea> estado = estadoTareaRepository.findById(1L);
			tarea.setEstado(estado.get());
			tarea = tareaRepository.save(tarea);
			return new ResponseEntity<String>("Tarea guardada correctamente!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<List<Tarea>> obtenerTareas() {
		try {
			List<Tarea> listaTareas = new ArrayList<>();
			tareaRepository.findAll().forEach(listaTareas::add);
			
			if(listaTareas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<List<Tarea>>(listaTareas, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	public ResponseEntity<?> verTarea(Long idTarea) {
		try {
			Optional<Tarea> t = tareaRepository.findById(idTarea);
			if(t.isEmpty()) {
				return new ResponseEntity<String>("Tarea no encontrada!!", HttpStatus.NOT_FOUND);
			}
			Optional<EstadoTarea> estado = estadoTareaRepository.findById(2L);
			t.get().setEstado(estado.get());
			tareaRepository.save(t.get());
			return new ResponseEntity<Tarea>(t.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<?> modificarTarea(Tarea tarea) {
		try {
			String nuevaDescripcion = tarea.getDescripcionTarea();
			Optional<Tarea> t = tareaRepository.findById(tarea.getId_tarea());
			if(t.isEmpty()) {
				return new ResponseEntity<String>("Tarea no encontrada!!", HttpStatus.NOT_FOUND);
			}
			Optional<EstadoTarea> estado = estadoTareaRepository.findById(3L);
			tarea = t.get();
			tarea.setDescripcionTarea(nuevaDescripcion);
			tarea.setEstado(estado.get());
			tarea = tareaRepository.save(tarea);
			return new ResponseEntity<Tarea>(tarea, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<?> terminarTarea(Long idTarea) {
		try {
			Optional<Tarea> t = tareaRepository.findById(idTarea);
			if(t.isEmpty()) {
				return new ResponseEntity<String>("Tarea no encontrada!!", HttpStatus.NOT_FOUND);
			}
			Optional<EstadoTarea> estado = estadoTareaRepository.findById(4L);
			t.get().setEstado(estado.get());
			tareaRepository.save(t.get());
			return new ResponseEntity<Tarea>(t.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<?> eliminarTarea(Long idTarea) {
		try {
			Optional<Tarea> t = tareaRepository.findById(idTarea);
			if(t.isEmpty()) {
				return new ResponseEntity<String>("Tarea no encontrada!!", HttpStatus.NOT_FOUND);
			}
			tareaRepository.deleteById(idTarea);
			return new ResponseEntity<String>("Tarea eliminada correctamente!!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
