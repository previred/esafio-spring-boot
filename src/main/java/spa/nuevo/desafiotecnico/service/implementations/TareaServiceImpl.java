/**
 * @ Author: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Create Time: 2024-01-11 21:37:51
 * @ Modified by: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Modified time: 2024-01-11 21:40:29
 * @ Description:
 */

package spa.nuevo.desafiotecnico.service.implementations;

import lombok.AllArgsConstructor;
import spa.nuevo.desafiotecnico.model.Tarea;
import spa.nuevo.desafiotecnico.repository.TareaRepository;
import spa.nuevo.desafiotecnico.service.TareaService;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TareaServiceImpl implements TareaService {
    private final TareaRepository tareaRepository;

    @Override
    public List<Tarea> findAll() {
        return tareaRepository.findAll();
    }

    @Override
    public Optional<Tarea> findById(Long id) {
        return tareaRepository.findById(id);
    }

    @Override
    public Tarea save(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    @Override
    public void delete(Tarea tarea) {
        tareaRepository.delete(tarea);
    }

}
