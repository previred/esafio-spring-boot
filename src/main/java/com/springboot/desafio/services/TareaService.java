package com.springboot.desafio.services;

import com.springboot.desafio.model.TareaEstRqDTO;
import com.springboot.desafio.model.TareaRqDTO;
import com.springboot.desafio.model.TareaRsDTO;

import java.util.List;

public interface TareaService {

    TareaRsDTO crearTarea(TareaRqDTO tareaRqDTO);
    void eliminarTarea(Long id);
    TareaRsDTO getTareaById(Long id);
    List<TareaRsDTO> findAll();
    TareaRsDTO updateTareaById(Long id, TareaEstRqDTO tareaRqDTO);

}
