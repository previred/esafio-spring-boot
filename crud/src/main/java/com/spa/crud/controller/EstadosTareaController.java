package com.spa.crud.controller;

import com.spa.crud.dto.EstadosTareaDTO;
import com.spa.crud.dto.RolesDTO;
import com.spa.crud.service.EstadosTareaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Estados Tareas Controller", description = "Endpoints para EstadosTareas")
@RestController
@RequestMapping("/estados")
public class EstadosTareaController {

    @Autowired
    private EstadosTareaService estadosTareaService;

    @Operation(summary = "Buscar todos los Estados de Tareas")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping()
    public @ResponseBody List<String> getEstados(){
        List<String> list = new ArrayList<>();
        try {
            list = estadosTareaService.getEstados();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
