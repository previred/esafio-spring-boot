package com.nuevospa.service;

import com.nuevospa.entity.EstadoTareaEntity;
import com.nuevospa.entity.TareaEntity;
import com.nuevospa.interfaces.TareaService;
import com.nuevospa.dto.TareaDTO;
import com.nuevospa.dto.TareaIdDTO;
import com.nuevospa.repository.EstadoTareaRepository;
import com.nuevospa.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Primary
public class TareaServiceImpl implements TareaService {

    @Autowired
    private TareaRepository tareaRepository;
    
    @Autowired
    private EstadoTareaRepository estadoTareaRepository;

    @Override
    public TareaDTO guardarTarea(TareaDTO tareaDTO, EstadoTareaEntity estadoTarea) {
       
    	// Lógica para obtener el EstadoTarea según el idEstadoTarea
        estadoTareaRepository.findById(tareaDTO.getIdEstadoTarea())
                .orElseThrow(() -> new RuntimeException("EstadoTarea no encontrado con ID: " + tareaDTO.getIdEstadoTarea()));

        return new TareaDTO(tareaRepository.save(new TareaEntity(tareaDTO,estadoTarea)));
    }

    @Override
    public List<TareaDTO> obtenerTareas() {
    	
    	List<TareaEntity> tareas = tareaRepository.findAll();
        return tareas.stream()
                .map(TareaDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TareaDTO> obtenerTareaPorId(Long id) {
    	
    	Optional<TareaEntity> tarea = tareaRepository.findById(id);
    	return tarea.map(TareaDTO::new);
    	
    }

    @Override
    public void eliminarTareaPorId(Long id) {
        tareaRepository.deleteById(id);
    }

	@Override
	public Optional<TareaDTO> actualizarTarea(TareaIdDTO tareaDTO, EstadoTareaEntity estadoTarea) {
		
		tareaRepository.save(new TareaEntity(tareaDTO, estadoTarea));
		Optional<TareaEntity> tarea = tareaRepository.findById(tareaDTO.getId());
    	return tarea.map(TareaDTO::new);
		
	}
}
