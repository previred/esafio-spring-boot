package cl.nuevospa.application.usecase;

import java.security.Key;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cl.nuevospa.application.port.out.UserRepository;
import cl.nuevospa.config.JWTGenerator;
import cl.nuevospa.config.exceptions.NotFoundException;
import cl.nuevospa.config.exceptions.errors.ErrorCode;
import cl.nuevospa.domain.h2.entities.UserDTO;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class LoginUseCase implements   UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDTO = userRepository.findByUsername(username).orElseThrow(()-> new NotFoundException(ErrorCode.USER_NOT_FOUND));
        
        
        return new UserDetailCustom(userDTO);
    }



}
