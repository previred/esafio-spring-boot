package cl.previred.desafip.msprevireddesafio.services;

import cl.previred.desafip.msprevireddesafio.DTO.LoginRequest;

public interface UserService {

    boolean findUser(LoginRequest loginReq);
}
