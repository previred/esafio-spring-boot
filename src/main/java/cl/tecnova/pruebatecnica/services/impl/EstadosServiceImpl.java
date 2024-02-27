package cl.tecnova.pruebatecnica.services.impl;

import cl.tecnova.pruebatecnica.dto.EstadoDTO;
import cl.tecnova.pruebatecnica.exception.HTTPException;
import cl.tecnova.pruebatecnica.model.EstadosTareaEntity;
import cl.tecnova.pruebatecnica.repositories.EstadosTareaRepository;
import cl.tecnova.pruebatecnica.services.EstadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstadosServiceImpl implements EstadosService {

    @Autowired
    private EstadosTareaRepository estadosTareaRepository;

    @Override
    public List<EstadoDTO> getEstados() {
        List<EstadosTareaEntity> estados = estadosTareaRepository.findAll();
        return estados.stream().map(EstadosTareaEntity::toDTO).collect(Collectors.toList());
    }

    @Override
    public EstadosTareaEntity getEstadoEntity(int id) throws HTTPException {
        Optional<EstadosTareaEntity> estadoTarea = estadosTareaRepository.findById(id);
        if (estadoTarea.isEmpty()) {
            throw new HTTPException(HttpStatus.BAD_REQUEST, "No existe un estado asociado al ID: " + id);
        }
        return estadoTarea.get();
    }

}
