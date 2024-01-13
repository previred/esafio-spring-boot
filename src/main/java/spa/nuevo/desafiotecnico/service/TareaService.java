/**
 * @ Author: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Create Time: 2024-01-11 21:36:34
 * @ Modified by: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Modified time: 2024-01-11 21:40:18
 * @ Description:
 */

package spa.nuevo.desafiotecnico.service;

import spa.nuevo.desafiotecnico.model.Tarea;

import java.util.List;
import java.util.Optional;

public interface TareaService {
    List<Tarea> findAll();

    Optional<Tarea> findById(Long id);

    Tarea save(Tarea tarea);

    void delete(Tarea tarea);
}
