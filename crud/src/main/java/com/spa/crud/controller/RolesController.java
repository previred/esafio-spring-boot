package com.spa.crud.controller;

import com.spa.crud.dto.RolesDTO;
import com.spa.crud.dto.UsuariosDTO;
import com.spa.crud.service.RolesService;
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

@Tag(name = "Roles Controller", description = "Endpoints para Roles")
@RestController
@RequestMapping("/roles")
public class RolesController {

    @Autowired
    private RolesService rolesService;

    @Operation(summary = "Buscar todos las Roles")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping()
    public @ResponseBody List<RolesDTO> getRoles(){
        List<RolesDTO> list = new ArrayList<>();
        try {
            list = rolesService.getRoles();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
