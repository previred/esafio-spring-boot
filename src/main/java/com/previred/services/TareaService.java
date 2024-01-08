package com.previred.services;

import com.previred.entities.TareaEntity;
import com.previred.repositories.TareaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cristian
 */
@Service("tareaService")
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;
    
    /**
     * metodo para almacenar tareas
     * @param tarea
     */
    public void guardar(TareaEntity tarea) {
        tareaRepository.save(tarea);
       
    }
    

    /**
     * metodo para eliminar tareas
     * @param idTarea
     */
    public void delete(Long idTarea) {
    	tareaRepository.deleteById(idTarea);
    }

   /**
    * obtiene todas las tareas
    * @return
    */
    public List <TareaEntity> getAllTareas() {
       return tareaRepository.findAll();
    }

    /**
     * se obtiene la tarea por id
     * @param idTarea
     * @return
     */
    public TareaEntity getTareaById(Long idTarea) {
        return tareaRepository.findById(idTarea).get();
    }
    /**
     * actualiza la tarea
     * @param tarea
     */
    public void update(TareaEntity tarea) {
    	TareaEntity tareaBD = tareaRepository.findById(tarea.getId()).get();
    	tareaBD.setEstadoTarea(tarea.getEstadoTarea());
    	tareaBD.setNombre(tarea.getNombre());
    	guardar(tareaBD);
    }

    
}
