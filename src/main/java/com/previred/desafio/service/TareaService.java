package com.previred.desafio.service;

import com.previred.desafio.dto.TareaDto;
import java.util.List;

/**
 * TareaService.
 *
 * @author Jimmy Villa.
 * @version 1.0.0, 23-01-2025
 */

public interface TareaService {
    List<TareaDto> getAllTareas();
    TareaDto getTareaById(Long id);
    TareaDto createTarea(TareaDto tareaDTO);
    TareaDto updateTarea(Long id, TareaDto tareaDTO);
    boolean deleteTarea(Long id);
}
