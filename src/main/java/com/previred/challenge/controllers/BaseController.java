package com.previred.challenge.controllers;

import com.previred.challenge.dto.LoggedUserDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class BaseController {

    protected LoggedUserDTO getLoggedUser() {
        return (LoggedUserDTO) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }

}
