package cl.nuevo.spa.desafio.service.impl;

import cl.nuevo.spa.desafio.model.EstadoTarea;
import cl.nuevo.spa.desafio.repository.EstadoTareaRepository;
import cl.nuevo.spa.desafio.service.EstadoTareaService;
import lombok.AllArgsConstructor;

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
        return estadoTareaRepository.save(estadoTarea);
    }

    @Override
    public void delete(EstadoTarea estadoTarea) {
        estadoTareaRepository.delete(estadoTarea);
    }

}
