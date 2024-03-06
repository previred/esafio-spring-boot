package com.spa.crud.service.impl;

import com.spa.crud.dto.EstadosTareaDTO;
import com.spa.crud.mapper.EstadosTareaMapper;
import com.spa.crud.repository.EstadosTareaRepository;
import com.spa.crud.service.EstadosTareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstadosTareaServiceImpl implements EstadosTareaService {

    @Autowired
    private EstadosTareaRepository estadosTareaRepository;

    @Autowired
    private EstadosTareaMapper estadoMapper;

    @Override
    public List<String> getEstados() {
        return estadosTareaRepository.getEstados();
    }
}
