/**
 * @ Author: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Create Time: 2024-01-12 19:35:39
 * @ Modified by: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Modified time: 2024-01-12 19:39:26
 * @ Description:
 */

package spa.nuevo.desafiotecnico.service;

import spa.nuevo.desafiotecnico.model.EstadoTarea;

import java.util.List;
import java.util.Optional;

public interface EstadoTareaService {
    List<EstadoTarea> findAll();

    Optional<EstadoTarea> findById(Integer id);

    EstadoTarea save(EstadoTarea tarea);

    void delete(EstadoTarea tarea);
}
