package com.nuevospa.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuevospa.dto.EstadoTareaDTO;
import com.nuevospa.entity.EstadoTareaEntity;
import com.nuevospa.interfaces.EstadoTareaService;
import com.nuevospa.repository.EstadoTareaRepository;

@Service
public class EstadoTareaServiceImpl implements EstadoTareaService {
	
    @Autowired
    private EstadoTareaRepository estadoTareaRepository;
    
    public EstadoTareaEntity guardarEstadoTarea(String nombre) {
        return estadoTareaRepository.save(new EstadoTareaEntity(nombre));
    }

    public List<EstadoTareaDTO> obtenerTodosEstadosTarea(){
    	List<EstadoTareaEntity> estados = estadoTareaRepository.findAll();
        return estados.stream()
                .map(EstadoTareaDTO::fromEntity)
                .collect(Collectors.toList());
     	
     }

    public Optional<Object> obtenerEstadoTareaPorId(Long id){
    	
    	Optional<EstadoTareaEntity> estadoTarea = estadoTareaRepository.findById(id);
    	return estadoTarea.map(EstadoTareaDTO::new);

    }

    public void eliminarEstadoTareaPorId(Long id) {
    	estadoTareaRepository.deleteById(id);
    }

	@Override
	public Optional<EstadoTareaDTO> actualizarEstadoTarea(EstadoTareaDTO estadoTareaDTO) {
		estadoTareaRepository.save(new EstadoTareaEntity(estadoTareaDTO.getId(), estadoTareaDTO.getNombre()));
		Optional<EstadoTareaEntity> estadoTarea = estadoTareaRepository.findById(estadoTareaDTO.getId());
    	return estadoTarea.map(EstadoTareaDTO::new);
	}

	@Override
	public EstadoTareaEntity obtenerEstadoTareaEntityPorId(Long id) {
		Optional<EstadoTareaEntity> optionalEstadoTareaEntity = estadoTareaRepository.findById(id);
	    return optionalEstadoTareaEntity.orElse(null);
	}


}