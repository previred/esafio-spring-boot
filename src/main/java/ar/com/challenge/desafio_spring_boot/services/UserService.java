package ar.com.challenge.desafio_spring_boot.services;

import ar.com.challenge.desafio_spring_boot.dto.SigninDto;
import ar.com.challenge.desafio_spring_boot.dto.SignupDto;
import ar.com.challenge.desafio_spring_boot.exception.ResourceFoundException;
import ar.com.challenge.desafio_spring_boot.exception.ResourceNotFoundException;

public interface UserService {

    String loadUser(SigninDto user) throws ResourceNotFoundException;

    String createUser(SignupDto user) throws ResourceNotFoundException, ResourceFoundException;
}
