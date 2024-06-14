package com.desafio.spring.ec.ws.expose.impl;

import com.desafio.spring.ec.bs.services.StatusServiceImpl;
import com.desafio.spring.ec.ds.dto.StatusTO;
import com.desafio.spring.ec.ws.expose.IStatusController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StatusController implements IStatusController {

    private final StatusServiceImpl statusService;

    @Autowired
    public StatusController(StatusServiceImpl statusService) {
        this.statusService = statusService;
    }

    @Override
    public ResponseEntity<StatusTO> getStatusById(Long id) throws Exception {
        return ResponseEntity.ok(statusService.findById(id));
    }

    @Override
    public ResponseEntity<List<StatusTO>> getAllStatus() throws Exception {
        return ResponseEntity.ok(statusService.findAll());
    }
}
