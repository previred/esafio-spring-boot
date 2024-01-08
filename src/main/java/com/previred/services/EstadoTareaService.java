package com.previred.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.previred.entities.EstadoTareaEntity;
import com.previred.repositories.EstadoTareaRepository;

@Service("estadoTareaService")
public class EstadoTareaService {
	
	 @Autowired
	 private EstadoTareaRepository estadoTareaRepository;
	 /**
	  * Funcion para almacenar tarea
	  * @param estadoTareaModel
	  */
	 public void guardar(EstadoTareaEntity estadoTareaModel) {
		 estadoTareaRepository.save(estadoTareaModel);
	 }
	/**
     * se obtiene la tarea por id
     * @param idTarea
     * @return
     */
    public EstadoTareaEntity getTareaById(Long idEstadpTarea) {
        return estadoTareaRepository.findById(idEstadpTarea).get();
    }

}
