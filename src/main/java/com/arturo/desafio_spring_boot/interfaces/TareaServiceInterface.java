package com.arturo.desafio_spring_boot.interfaces;

import java.util.List;
import java.util.Optional;

import com.arturo.desafio_spring_boot.dtos.TareaDto;
import com.arturo.desafio_spring_boot.entities.TareaEntity;
import com.arturo.desafio_spring_boot.exception.DataNotFoundException;
import com.arturo.desafio_spring_boot.exception.DataNotProvidedException;

public interface TareaServiceInterface {
    
    public List<TareaEntity> getAll();
    public List<TareaEntity> getByEstado(Long estadoId);
    public Optional<TareaEntity> getById(Long id);
    public TareaEntity create(TareaDto tareaEntity) throws DataNotFoundException, DataNotProvidedException;
    public TareaEntity update(TareaDto tareaEntity) throws DataNotFoundException, DataNotProvidedException;
    public TareaEntity patch(TareaDto tareaEntity) throws DataNotFoundException, DataNotProvidedException;
    public TareaEntity delete(Long id) throws DataNotFoundException;

}
