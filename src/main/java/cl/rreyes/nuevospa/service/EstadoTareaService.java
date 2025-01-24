package cl.rreyes.nuevospa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.rreyes.nuevospa.model.EstadoTarea;
import cl.rreyes.nuevospa.repository.EstadoTareaRepository;

@Service
public class EstadoTareaService {
	@Autowired
	EstadoTareaRepository estadoTareaRepository;
	public EstadoTarea createEstadoTarea(EstadoTarea estadoTarea) {		
		return estadoTareaRepository.save(estadoTarea);
	}
}
