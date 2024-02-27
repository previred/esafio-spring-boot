package cl.tecnova.pruebatecnica.services.impl;

import cl.tecnova.pruebatecnica.dto.TareaDTO;
import cl.tecnova.pruebatecnica.dto.UpdateTareaRequest;
import cl.tecnova.pruebatecnica.exception.HTTPException;
import cl.tecnova.pruebatecnica.model.EstadosTareaEntity;
import cl.tecnova.pruebatecnica.model.TareasEntity;
import cl.tecnova.pruebatecnica.repositories.TareasRepository;
import cl.tecnova.pruebatecnica.services.EstadosService;
import cl.tecnova.pruebatecnica.services.TareasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TareasServiceImpl implements TareasService {

    @Autowired
    private TareasRepository tareasRepository;

    @Autowired
    private EstadosService estadosService;

    @Override
    public TareaDTO createTarea(UpdateTareaRequest request) throws HTTPException {
        if (!StringUtils.hasText(request.getNombre())) {
            throw new HTTPException(HttpStatus.BAD_REQUEST, "Debe ingresar nombre a la tarea.");
        }
        TareasEntity entity = new TareasEntity();
        entity.setNombre(request.getNombre());
        entity.setEstado(estadosService.getEstadoEntity(request.getIdEstado()));
        entity = tareasRepository.save(entity);

        return entity.toDTO();
    }

    @Override
    public TareaDTO getTarea(int id) throws HTTPException {
        return getTareaEntity(id).toDTO();
    }

    @Override
    public List<TareaDTO> getAllTareas() {
        return tareasRepository.findAll().stream().map(TareasEntity::toDTO).collect(Collectors.toList());
    }

    @Override
    public TareaDTO updateTarea(int id, UpdateTareaRequest request) throws HTTPException {
        TareasEntity entity = getTareaEntity(id);

        if (!StringUtils.hasText(request.getNombre())) {
            throw new HTTPException(HttpStatus.BAD_REQUEST, "Debe ingresar nombre a la tarea.");
        }
        if (request.getNombre().equals(entity.getNombre()) && request.getIdEstado() == entity.getEstado().getId()) {
            throw new HTTPException(HttpStatus.BAD_REQUEST, "Solicitud no presenta cambios en la tarea.");
        }

        EstadosTareaEntity estado = estadosService.getEstadoEntity(request.getIdEstado());
        entity.setNombre(request.getNombre());
        entity.setEstado(estado);
        entity = tareasRepository.save(entity);

        return entity.toDTO();
    }

    @Override
    public void eliminarTarea(int id) throws HTTPException {
        TareasEntity entity = getTareaEntity(id);
        tareasRepository.delete(entity);
        tareasRepository.flush();
    }

    private TareasEntity getTareaEntity(int id) throws HTTPException {
        Optional<TareasEntity> entity = tareasRepository.findById(id);
        if (entity.isEmpty()) {
            throw new HTTPException(HttpStatus.BAD_REQUEST, "No existe una tarea asociada al ID: " + id);
        }

        return entity.get();
    }

}
