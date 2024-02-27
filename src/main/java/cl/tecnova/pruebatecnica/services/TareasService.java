package cl.tecnova.pruebatecnica.services;

import cl.tecnova.pruebatecnica.dto.TareaDTO;
import cl.tecnova.pruebatecnica.dto.UpdateTareaRequest;
import cl.tecnova.pruebatecnica.exception.HTTPException;

import java.util.List;

public interface TareasService {

    TareaDTO createTarea(UpdateTareaRequest request) throws HTTPException;

    TareaDTO getTarea(int id) throws HTTPException;

    List<TareaDTO> getAllTareas();

    TareaDTO updateTarea(int id, UpdateTareaRequest request) throws HTTPException;

    void eliminarTarea(int id) throws HTTPException;

}
