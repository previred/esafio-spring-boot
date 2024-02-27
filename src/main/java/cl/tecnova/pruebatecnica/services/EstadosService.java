package cl.tecnova.pruebatecnica.services;

import cl.tecnova.pruebatecnica.dto.EstadoDTO;
import cl.tecnova.pruebatecnica.exception.HTTPException;
import cl.tecnova.pruebatecnica.model.EstadosTareaEntity;

import java.util.List;

public interface EstadosService {

    List<EstadoDTO> getEstados();

    EstadosTareaEntity getEstadoEntity(int id) throws HTTPException;

}
