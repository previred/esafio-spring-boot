package com.example.demo.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.error.PrimaryKeyViolationException;
import com.example.demo.jwt.JwtService;
import com.example.demo.model.Role;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	@Autowired 
	UsuarioRepository userRepository;
	@Autowired
	JwtService jwtService;
	@Autowired
	PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            Usuario user = userRepository.findByUsername(request.getUsername()).orElseThrow();
            String token = jwtService.getToken(user);
            
            return AuthResponse.builder()
                .token(token)
                .code(200) // Código de éxito
                .message("Usuario autenticado exitosamente")
                .build();
        } catch (AuthenticationException ex) {
            // Manejar el error de autenticación y retornar una respuesta con el código de estado 403
            return AuthResponse.builder()
                    .code(403) // Código de estado 403 (Forbidden)
                    .message("Error de autenticación: " + ex.getMessage())
                    .build();
        }
    }

    public  AuthResponse register(RegisterRequest request) {
    	try {
    	Optional<Usuario> existingUser = userRepository.findByUsername(request.getUsername());
        
        // Verificar si el usuario ya existe
        if (existingUser.isPresent()) {
            throw new DataIntegrityViolationException("El usuario ya existe");
        }
        
        Usuario user = new Usuario();
        	   user.setUsername(request.getUsername());
        	    user.setPassword(passwordEncoder.encode(request.getPassword()));
        	    user.setFirstname(request.getFirstname());
        	    user.setLastname(request.getLastname());	
        	    user.setCountry(request.getCountry());
        	   user.setRole( Role.USER);
    
        userRepository.save(user);

        return AuthResponse.builder()
            .token(jwtService.getToken(user))
            .code(201) // Código de éxito
            .message("Usuario registrado correctamente")
            .build();
        
    	}catch (DataIntegrityViolationException ex) {
    	     return AuthResponse.builder()
    	                    .code(403) // Código de estado 403 (Forbidden)
    	                    .message("Error al crear el usuario: " + ex.getMessage())
    	                    .build();
        }catch (PrimaryKeyViolationException e) {
        	 return AuthResponse.builder()
	                    .code(400) // Código de estado 403 (Forbidden)
	                    .message("Error al crear el usuario: " + e.getMessage())
	                    .build();
        }
        
    }

}
