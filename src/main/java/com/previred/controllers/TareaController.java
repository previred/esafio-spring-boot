package com.previred.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.previred.dto.RespuestaRestDTO;
import com.previred.dto.TareaDTO;
import com.previred.entities.EstadoTareaEntity;
import com.previred.entities.TareaEntity;
import com.previred.services.EstadoTareaService;
import com.previred.services.TareaService;
import com.previred.utils.Constantes;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;

/**
 * @author cristian
 */
@RestController
public class TareaController {
	
      private static final Logger logger = LogManager.getLogger(TareaController.class);	  
      @Autowired
	  private TareaService servicioTarea;
	  @Autowired
	  private EstadoTareaService servicioEstadoTarea;
	  /**
	   * 
	   * @param json
	   * @return
	   */
	  @PostMapping("guardarTarea")
	  public RespuestaRestDTO  crearTarea(@RequestBody TareaDTO td) {
		  	RespuestaRestDTO rrd = new RespuestaRestDTO();
		  	try {
		  		//Gson gson = new GsonBuilder().create();
				//TareaDTO td = gson.fromJson(json, TareaDTO.class);
				TareaEntity tm = new TareaEntity();
		    	EstadoTareaEntity et = servicioEstadoTarea.getTareaById(td.getIdEstadoTarea());
		    	tm.setEstadoTarea(et);
		    	tm.setNombre(td.getNombre());
		    	servicioTarea.guardar(tm);
		    	rrd.setEstado(Constantes.RESPUESTA_REST_OK);
		    	rrd.setComentario("Tarea Almacenado con exito");
		    	return rrd;
		  }catch(Exception e) {
			  	rrd.setEstado(Constantes.RESPUESTA_REST_FAIL);
		    	rrd.setComentario("Problemas al almacenar la tarea: " + e.toString());
		    	logger.error("No se pudo guardar la tarea", e);
		    	return rrd;
				
		  }
		
			
	  }
	  /**
	   * Funcion para obtener todos los estados de las tareas
	   * @return
	   */
	  @PostMapping("getAllTareas")
	  public List<TareaDTO> listarTareas() {
		  List<TareaDTO> tareasDTO = null;
		  try {
			  List<TareaEntity> tareasEntity = servicioTarea.getAllTareas();
			  tareasDTO = new ArrayList<>();
			  for (int i = 0; i < tareasEntity.size(); i++) {
				  TareaDTO tdto = new TareaDTO();
				  tdto.setDescripcionEstadoTarea(tareasEntity.get(i).getEstadoTarea().getDescripcion());
				  tdto.setId(tareasEntity.get(i).getId());
				  tdto.setIdEstadoTarea(tareasEntity.get(i).getEstadoTarea().getId());
				  tdto.setNombre(tareasEntity.get(i).getNombre());
				  tareasDTO.add(tdto);
			  }
			  
		  }catch(Exception e) {
			  	logger.error("No se pudo obtener la lista de tareas", e);
		    	return tareasDTO;
		  }
		  return tareasDTO;
	  }
	  /**
	   * Funcion para actualizar la tarea
	   * @param json
	   * @return
	   */
	  @PostMapping("actualizarTarea")
	  public RespuestaRestDTO actualizarTarea(@RequestBody TareaDTO td) {
		  RespuestaRestDTO rrd = new RespuestaRestDTO();
		  try {
			  //Gson gson = new GsonBuilder().create();
			  //TareaDTO td = gson.fromJson(json, TareaDTO.class);
			  TareaEntity tareaEntity = servicioTarea.getTareaById(td.getId());
			  tareaEntity.setNombre(td.getNombre());
			  EstadoTareaEntity ete = servicioEstadoTarea.getTareaById(td.getIdEstadoTarea());
			  tareaEntity.setEstadoTarea(ete);
			  servicioTarea.update(tareaEntity);
			  rrd.setEstado(Constantes.RESPUESTA_REST_OK);
			  rrd.setComentario("Tarea Actualizada con exito");
			  return rrd;
		  }catch(Exception e) {
			  rrd.setEstado(Constantes.RESPUESTA_REST_FAIL);
			  rrd.setComentario("Problemas al actualizar la tarea: " + e.toString());
			  logger.error("No se pudo actualizar la tarea", e);
			  return rrd;
		  }
		
	  }
	  /**
	   * Funcion para eliminar la tarea
	   * @param json
	   * @return
	   */
	  @PostMapping("eliminarTarea")
	  public RespuestaRestDTO eliminarTarea(@RequestBody TareaDTO td) {
		  RespuestaRestDTO rrd = new RespuestaRestDTO();
		  try {
			  //Gson gson = new GsonBuilder().create();
			  //TareaDTO td = gson.fromJson(json, TareaDTO.class);
			  servicioTarea.delete(td.getId());
			  rrd.setEstado(Constantes.RESPUESTA_REST_OK);
			  rrd.setComentario("Tarea Eliminada con exito");
			  return rrd;
		  }catch(Exception e) {
			  rrd.setEstado(Constantes.RESPUESTA_REST_FAIL);
			  rrd.setComentario("Problemas al eliminar la tarea: " + e.toString());
			  logger.error("No se pudo eliminar la tarea", e);
			  return rrd;
		  }
		
	  }
}
