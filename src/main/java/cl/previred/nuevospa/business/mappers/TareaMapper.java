package cl.previred.nuevospa.business.mappers;

import cl.previred.nuevospa.dto.EstadoDto;
import cl.previred.nuevospa.dto.TareaDto;
import cl.previred.nuevospa.entities.EstadoTarea;
import cl.previred.nuevospa.entities.Tarea;

public class TareaMapper {
    public static TareaDto tareaToTareaDto (Tarea tarea){
        return new TareaDto(tarea.getId(), 
            tarea.getTitulo(),
            tarea.getDescripcion(),
            tarea.getFechaCreacion(),
            tarea.getFechaActualizacion(),
            estadoTareaToEstadoDto(tarea.getEstado()),
            UsuarioMapper.usuarioToUsuarioDto(tarea.getUsuario()));
    }

    public static EstadoDto estadoTareaToEstadoDto(EstadoTarea estadoTarea){
        return new EstadoDto(estadoTarea.getId(), estadoTarea.getNombre());
    }

}
