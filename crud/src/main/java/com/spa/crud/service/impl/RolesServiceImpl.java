package com.spa.crud.service.impl;

import com.spa.crud.dto.RolesDTO;
import com.spa.crud.dto.TareasDTO;
import com.spa.crud.mapper.RolesMapper;
import com.spa.crud.repository.RolesRepository;
import com.spa.crud.service.RolesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private RolesMapper mapper;

    @Override
    public List<RolesDTO> getRoles() {
        return rolesRepository.findAll().stream().map(mapper::rolesToDto).collect(Collectors.toList());
    }
}
