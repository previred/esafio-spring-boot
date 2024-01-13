/**
 * @ Author: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Create Time: 2024-01-12 19:37:00
 * @ Modified by: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Modified time: 2024-01-12 20:35:57
 * @ Description:
 */

package spa.nuevo.desafiotecnico.service.implementations;

import lombok.AllArgsConstructor;
import spa.nuevo.desafiotecnico.model.EstadoTarea;
import spa.nuevo.desafiotecnico.repository.EstadoTareaRepository;
import spa.nuevo.desafiotecnico.service.EstadoTareaService;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EstadoTareaServiceImpl implements EstadoTareaService {
    private final EstadoTareaRepository estadoTareaRepository;

    @Override
    public List<EstadoTarea> findAll() {
        return estadoTareaRepository.findAll();
    }

    @Override
    public Optional<EstadoTarea> findById(Integer id) {
        return estadoTareaRepository.findById(id);
    }

    @Override
    public EstadoTarea save(EstadoTarea estadoTarea) {
        if (estadoTarea.getId() == null) {
            Optional<Integer> maxID = estadoTareaRepository.findAll()
                    .stream().map(EstadoTarea::getId)
                    .max(Integer::compare);
            estadoTarea.setId(maxID.orElse(0) + 1);
        }
        return estadoTareaRepository.save(estadoTarea);
    }

    @Override
    public void delete(EstadoTarea estadoTarea) {
        estadoTareaRepository.delete(estadoTarea);
    }

}
