package co.com.task.api.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import co.com.task.api.config.SecurityConfig;
import co.com.task.api.domain.Session;
import co.com.task.api.domain.User;
import co.com.task.api.dto.LoginRequestDTO;
import co.com.task.api.dto.LoginResponseDTO;
import co.com.task.api.exceptions.ExceptionManager;
import co.com.task.api.repository.SessionRepository;
import co.com.task.api.repository.UserRepository;

@Service
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final JwtUtilService jwtUtilService;


    public SessionServiceImpl(SessionRepository sessionRepository, UserRepository userRepository,
	    SecurityConfig securityConfig, JwtUtilService jwtUtilService) {
	this.sessionRepository = sessionRepository;
	this.userRepository = userRepository;
	this.jwtUtilService=jwtUtilService;
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequest) {
	return userRepository
		.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPass())
		.map(user -> createSessionToken(user, loginRequest))
		.orElseThrow(() -> {
		    throw new ExceptionManager().new LoginException();
		});
    }

    private LoginResponseDTO createSessionToken(User user, LoginRequestDTO loginRequestDTO) {

	GrantedAuthority rol = new SimpleGrantedAuthority("USER");
	List<GrantedAuthority> listGrantedAuthority = new ArrayList<>();
	listGrantedAuthority.add(rol);
	UserDetails userDetails = new org.springframework.security.core.userdetails.User(loginRequestDTO.getEmail(),
		loginRequestDTO.getPass(), listGrantedAuthority);

	Session newSession = new Session();
	newSession.setActive(false);
	newSession.setLastLogin(LocalDateTime.now());
	newSession.setToken(jwtUtilService.generateToken(userDetails));
	newSession.setUser(user);

	sessionRepository.save(newSession);

	return LoginResponseDTO.builder().token(newSession.getToken()).build();

    }

}
