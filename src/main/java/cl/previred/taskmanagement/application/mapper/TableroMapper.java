package cl.previred.taskmanagement.application.mapper;

import cl.previred.taskmanagement.application.dto.response.TableroDTO;
import cl.previred.taskmanagement.core.domain.entities.Tablero;

public class TableroMapper {

    public static TableroDTO mapperTablero(Tablero entidad){
        TableroDTO dto = new TableroDTO();
        dto.setCodigo(entidad.getCodigo());
        dto.setNombre(entidad.getNombre());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setFechaCreacion(entidad.getFechaCreacion());
        return dto;
    }
}
