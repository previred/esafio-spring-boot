package cl.gestiontareasprevired.service;

import cl.gestiontareasprevired.dto.TareaDto;
import cl.gestiontareasprevired.dto.TareaReqDto;

import java.util.List;
import java.util.Optional;

public interface TareasService {

    List<TareaDto> getAllTareas();

    Optional<TareaDto> getTareaPorId(String id);

    void eliminarTareaPorId(String idTarea);

    String crearTarea(TareaReqDto tareaReqDto);

    void actualizarTarea(String idTarea, TareaReqDto tareaReqDto);
}
