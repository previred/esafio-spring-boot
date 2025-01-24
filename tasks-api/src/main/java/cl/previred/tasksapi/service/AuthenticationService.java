package cl.previred.tasksapi.service;

import cl.previred.tasksapi.dto.AuthenticationRequestDTO;
import cl.previred.tasksapi.dto.AuthenticationResponseDTO;

public interface AuthenticationService {

    /**
     *
     * @param request
     * @return
     */
    AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request);
}
