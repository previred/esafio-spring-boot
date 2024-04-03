package com.nuevospa.app.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EntityMapper {
    private final ModelMapper modelMapper;

    public EntityMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <D, T> void mapDTOToEntity(D dto, T entity) {
        modelMapper.map(dto, entity);
    }
}