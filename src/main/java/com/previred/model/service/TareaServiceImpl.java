package com.previred.model.service;

import com.previred.dto.Response;
import com.previred.dto.ResponseError;
import com.previred.model.dao.TareaDao;
import com.previred.model.entitys.Tarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareaServiceImpl implements TareaService{

    @Autowired
    private TareaDao tareaDao;

    @Override
    public ResponseEntity<?> agregarTarea(Tarea tarea) {
        try {
            Response response=new Response(0,"200","Tarea agregada",null);
            response.setTarea(tareaDao.save(tarea));
            return ResponseEntity.ok(response);
        }catch (Exception exception){
            ResponseError response=new ResponseError();
            response.setEstado(1);
            response.setCodigo("500");
            response.setMensaje("Error al momento de agregar la nueva tarea");
            return ResponseEntity.ok(response);
        }
    }

    @Override
    public ResponseEntity<?> agregarTareas(List<Tarea> tareas) {
        try {
            Response response=new Response(0,"200","Tareas agregadas",null);
            tareaDao.saveAll(tareas);
            return ResponseEntity.ok(response);
        }catch (Exception exception){
            ResponseError response=new ResponseError();
            response.setEstado(1);
            response.setCodigo("500");
            response.setMensaje("Error al momento de guardar las tareas");
            return ResponseEntity.ok(response);
        }
    }

    @Override
    public ResponseEntity<?> actualizarTarea(Tarea tarea) {
        try {
            Response response=new Response(0,"200","Tarea actualizada",null);
            if(tarea.getId()==null){
                response.setMensaje("El ID de la tarea no puede ser nulo");
            }else {
                response.setTarea(tareaDao.save(tarea));
            }
            return ResponseEntity.ok(response);
        }catch (Exception exception){
            ResponseError response=new ResponseError();
            response.setEstado(1);
            response.setCodigo("500");
            response.setMensaje("Error al momento de actualizar la tarea ID "+tarea.getId());
            return ResponseEntity.ok(response);
        }
    }

    @Override
    public ResponseEntity<?> eliminarTarea(Long id) {
        try {
            Response response=new Response(0,"200","Tarea eliminada",null);
            Tarea tarea=tareaDao.findById(id).get();
            response.setTarea(tarea);
            tareaDao.deleteById(id);
            return ResponseEntity.ok(response);
        }catch (Exception exception){
            ResponseError response=new ResponseError();
            response.setEstado(1);
            response.setCodigo("500");
            response.setMensaje("Error al momento de eliminar la tarea ID "+id);
            return ResponseEntity.ok(response);
        }
    }

    @Override
    public ResponseEntity<?> mostrarTarea(Long id) {
        try {
            Response response=new Response(0,"200","",null);
            Optional<Tarea> tarea=tareaDao.findById(id);
            tarea.ifPresent(response::setTarea);
            return ResponseEntity.ok(response);
        }catch (Exception exception){
            ResponseError response=new ResponseError(1,"500","Error al momento de eliminar la tarea ID "+id);
            return ResponseEntity.ok(response);
        }
    }


}
