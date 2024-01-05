package com.moveapps.taskmanager.controller;

import java.net.URI;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public abstract class AbstractController {

    protected URI getLocationResource(String externalId){
        return ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(externalId)
            .toUri();
    }

}
