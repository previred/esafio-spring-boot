package com.moveapps.pe;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.moveapps.pe.controller.AuthenticationController;
import com.moveapps.pe.dto.request.SignUpRequest;
import com.moveapps.pe.dto.request.SigninRequest;
import com.moveapps.pe.dto.response.JwtAuthenticationResponse;
import com.moveapps.pe.entities.User;
import com.moveapps.pe.service.AuthenticationService;
import com.moveapps.pe.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserControllerTest {
	 

	    @Mock
	    private AuthenticationService authenticationService;
 

	    @InjectMocks
	    private AuthenticationController authenticationControllern;


	   /* @Test
	    void registerUser() {
	    	SignUpRequest sign = new SignUpRequest();
	    	JwtAuthenticationResponse jwtResponse= new JwtAuthenticationResponse();
	        User user = new User();
	        when(authenticationService.signup(any(SignUpRequest.class))).thenReturn(jwtResponse);

	        ResponseEntity<JwtAuthenticationResponse> responseEntity = authenticationControllern.signup(sign);

	        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	        assertEquals(jwtResponse, responseEntity.getBody());
	    }

	    @Test
	    void authenticate() {
	    	SignUpRequest sign = new SignUpRequest();
	    	JwtAuthenticationResponse jwtResponse= new JwtAuthenticationResponse();
	        User user = new User();
	        when(authenticationService.signin(any(SigninRequest.class))).thenReturn(jwtResponse);

	        ResponseEntity<JwtAuthenticationResponse> responseEntity = authenticationControllern.signup(sign);

	        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	        assertEquals(jwtResponse, responseEntity.getBody());
	    }*/
	 
}
