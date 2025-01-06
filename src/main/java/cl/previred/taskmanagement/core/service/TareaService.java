package cl.previred.taskmanagement.core.service;


import cl.previred.taskmanagement.application.dto.request.ActualizarTareaRequest;
import cl.previred.taskmanagement.application.dto.request.CrearTareaRequest;
import cl.previred.taskmanagement.application.dto.response.RespuestaDTO;
import cl.previred.taskmanagement.core.domain.entities.Tarea;

public interface TareaService {
    RespuestaDTO crear(CrearTareaRequest tareaRequest);
    RespuestaDTO obtenerTodas();
    RespuestaDTO obtenerTareasUsuario(String usuario);
    RespuestaDTO actualizarTarea(Long codigo, ActualizarTareaRequest actualizarTareaRequest);
    RespuestaDTO eliminarTarea(Long codigo);
}
