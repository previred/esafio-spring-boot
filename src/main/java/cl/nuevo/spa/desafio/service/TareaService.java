package cl.nuevo.spa.desafio.service;

import cl.nuevo.spa.desafio.dto.TareaDTO;
import cl.nuevo.spa.desafio.model.Tarea;

import java.util.List;
import java.util.Optional;

public interface TareaService {
    List<TareaDTO> findAll();

    Optional<TareaDTO> findById(Long id);

    TareaDTO save(Tarea tarea);

    void delete(Long id);
}
