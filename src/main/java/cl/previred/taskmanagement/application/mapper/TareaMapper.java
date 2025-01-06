package cl.previred.taskmanagement.application.mapper;

import cl.previred.taskmanagement.application.dto.response.TareaDTO;
import cl.previred.taskmanagement.core.domain.entities.Tarea;

public class TareaMapper {


    public static TareaDTO mapperTarea(Tarea entidad, Boolean mostrarInfoTablero){
        TareaDTO dto = new TareaDTO();
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(TareaDTO.Estado.fromValue(entidad.getEstado()));
        dto.setUsuario(entidad.getUsuario() != null ? entidad.getUsuario().getUsuario() : null);
        dto.setFechaCreacion(entidad.getFechaCreacion());
        dto.setFechaAsignacion(entidad.getFechaAsignacion());
        dto.setFechaTermino(entidad.getFechaTermino());
        if(mostrarInfoTablero){
            dto.setTablero(TableroMapper.mapperTablero(entidad.getTablero()));
        }
        return dto;
    }
}
