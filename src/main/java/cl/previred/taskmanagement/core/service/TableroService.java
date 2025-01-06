package cl.previred.taskmanagement.core.service;

import cl.previred.taskmanagement.application.dto.request.CrearTableroRequest;
import cl.previred.taskmanagement.application.dto.response.RespuestaTableroDTO;

public interface TableroService {

    RespuestaTableroDTO crearTablero(CrearTableroRequest request);
    RespuestaTableroDTO buscarTableroPorCodigo(String codio);
}
