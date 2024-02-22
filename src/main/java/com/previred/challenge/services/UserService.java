package com.previred.challenge.services;

import com.previred.challenge.dto.UserLoginRequestDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    String authenticate(UserLoginRequestDTO userLoginRequestDTO);

}
