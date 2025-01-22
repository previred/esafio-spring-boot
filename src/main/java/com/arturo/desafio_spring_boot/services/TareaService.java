package com.arturo.desafio_spring_boot.services;

import java.util.List;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.arturo.desafio_spring_boot.dtos.TareaDto;
import com.arturo.desafio_spring_boot.entities.TareaEntity;
import com.arturo.desafio_spring_boot.entities.UsuarioEntity;
import com.arturo.desafio_spring_boot.exception.DataNotFoundException;
import com.arturo.desafio_spring_boot.exception.DataNotProvidedException;
import com.arturo.desafio_spring_boot.interfaces.TareaServiceInterface;
import com.arturo.desafio_spring_boot.repositories.EstadoTareaRepository;
import com.arturo.desafio_spring_boot.repositories.TareaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TareaService implements TareaServiceInterface {

    private TareaRepository tareaRepository;
    private EstadoTareaRepository estadoTareaRepository;
    private static final String DATA_NO_PROPORCIONADA = "Data no proporcionada";
    private static final String ID_NO_PROPORCIONADO = "Identificador de tarea no proporcionado";
    private static final String TAREA_NO_EXISTE_MSG = "Tarea con id proporcionado no existe";
    private static final String ESTADO_TAREA_NO_EXISTE_MSG = "Estado de tarea con id proporcionado no existe";

    @Override
    public List<TareaEntity> getAll() {
        return this.tareaRepository.findAll();
    }

    @Override
    public List<TareaEntity> getByEstado(Long estadoId) {
        final var estadoTareaBd = this.estadoTareaRepository.findById(estadoId);
        if (estadoTareaBd.isEmpty()) {
            return List.of();
        }
        return this.tareaRepository.findByEstado(estadoTareaBd.get());
    }

    @Override
    public Optional<TareaEntity> getById(Long id) {
        return this.tareaRepository.findById(id);
    }

    @Override
    public TareaEntity create(TareaDto tareaEntity) throws DataNotProvidedException {
        if (tareaEntity.getNombre() == null || tareaEntity.getDescripcion() == null) {
            throw new DataNotProvidedException(DATA_NO_PROPORCIONADA);
        }
        if (tareaEntity.getEstado_tarea_id() == null) {
            throw new DataNotProvidedException(DATA_NO_PROPORCIONADA);
        }
        final var estadoTareaBd = this.estadoTareaRepository.findById(tareaEntity.getEstado_tarea_id());
        if (estadoTareaBd.isEmpty()) {
            throw new DataNotProvidedException(ESTADO_TAREA_NO_EXISTE_MSG);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsuarioEntity usuario = (UsuarioEntity)authentication.getPrincipal();

        final var tareaCreate = TareaEntity.builder()
            .nombre(tareaEntity.getNombre().trim())
            .descripcion(tareaEntity.getDescripcion().trim())
            .estado(estadoTareaBd.get())
            .usuario(usuario)
            .build();

        return this.tareaRepository.save(tareaCreate);
    }

    @Override
    public TareaEntity update(TareaDto tareaEntity) throws DataNotFoundException, DataNotProvidedException {
        if (tareaEntity.getId().isEmpty()) {
            throw new DataNotProvidedException(ID_NO_PROPORCIONADO);
        }
        if (tareaEntity.getDescripcion() == null || tareaEntity.getNombre() == null || tareaEntity.getEstado_tarea_id() == null) {
            throw new DataNotProvidedException(DATA_NO_PROPORCIONADA);
        }
        final var tareaId = tareaEntity.getId().get();
        final var tareaBD = this.tareaRepository.findById(tareaId);
        if (tareaBD.isEmpty()) {
            throw new DataNotFoundException(TAREA_NO_EXISTE_MSG);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsuarioEntity usuario = (UsuarioEntity)authentication.getPrincipal();

        final var estadoTareaBd = this.estadoTareaRepository.findById(tareaEntity.getEstado_tarea_id());
        if (estadoTareaBd.isEmpty()) {
            throw new DataNotFoundException(ESTADO_TAREA_NO_EXISTE_MSG);
        }

        final var tareaUpdate = TareaEntity.builder()
            .id(tareaId)
            .nombre(tareaEntity.getNombre())
            .descripcion(tareaEntity.getDescripcion())
            .estado(estadoTareaBd.get())
            .usuario(usuario)
            .build();

        return this.tareaRepository.save(tareaUpdate);
    }

    @Override
    public TareaEntity patch(TareaDto tareaEntity) throws DataNotFoundException, DataNotProvidedException {
        if (tareaEntity.getId().isEmpty()) {
            throw new DataNotProvidedException(ID_NO_PROPORCIONADO);
        }
        final var tareaId = tareaEntity.getId().get();
        final var tareaBD = this.tareaRepository.findById(tareaId);
        if (tareaBD.isEmpty()) {
            throw new DataNotFoundException(TAREA_NO_EXISTE_MSG);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsuarioEntity usuario = (UsuarioEntity)authentication.getPrincipal();

        final var tareaUpdate = TareaEntity.builder()
            .id(tareaId)
            .nombre(tareaBD.get().getNombre())
            .descripcion(tareaBD.get().getDescripcion())
            .estado(tareaBD.get().getEstado())
            .usuario(usuario)
            .build();

        if(tareaEntity.getNombre() != null) {
            tareaUpdate.setNombre(tareaEntity.getNombre());
        }

        if(tareaEntity.getDescripcion() != null) {
            tareaUpdate.setDescripcion(tareaEntity.getDescripcion());
        }

        if(tareaEntity.getEstado_tarea_id() != null) {
            final var estadoTareaBd = this.estadoTareaRepository.findById(tareaEntity.getEstado_tarea_id());
            if (estadoTareaBd.isEmpty()) {
                throw new DataNotFoundException(ESTADO_TAREA_NO_EXISTE_MSG);
            }
            tareaUpdate.setEstado(estadoTareaBd.get());
        }

        return this.tareaRepository.save(tareaUpdate);
    }

    @Override
    public TareaEntity delete(Long id) throws DataNotFoundException {
        final var tareaBD = this.tareaRepository.findById(id);
        if (tareaBD.isEmpty()) {
            throw new DataNotFoundException(TAREA_NO_EXISTE_MSG);
        }

        this.tareaRepository.delete(tareaBD.get());
        return tareaBD.get();
    }
    
}
