package cl.previred.taskmanagement.core.service;

import cl.previred.taskmanagement.application.dto.response.RespuestaTokenDTO;

public interface AuthService {
    RespuestaTokenDTO login(String username, String password);
    RespuestaTokenDTO validateToken(String token);
}
