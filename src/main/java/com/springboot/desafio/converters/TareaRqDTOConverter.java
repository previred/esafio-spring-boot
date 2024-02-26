package com.springboot.desafio.converters;

import com.springboot.desafio.model.Tarea;
import com.springboot.desafio.model.TareaRqDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class TareaRqDTOConverter implements Converter<TareaRqDTO, Tarea> {

    @Override
    public Tarea convert(TareaRqDTO source) {
        Tarea tarea = new Tarea();
        tarea.setNombre(source.getNombre());
        tarea.setDescripcion(source.getDescripcion());
        tarea.setStoryPoint(source.getStoryPoint());
        tarea.setUsuarioId(source.getUsuarioId());
        return tarea;
    }

}
