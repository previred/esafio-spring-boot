package com.previred.desafioGestionTareas.services;

import com.previred.desafioGestionTareas.dtos.TareaDTO;
import java.util.List;
import java.util.Optional;

public interface TareaServices {

    List<TareaDTO> findAll();

    Optional<TareaDTO> findById(Long id);
    void save(TareaDTO tareaDAO);

    Optional<TareaDTO> update(Long id, TareaDTO taskDTO );

    void deleteById(Long id);

}
