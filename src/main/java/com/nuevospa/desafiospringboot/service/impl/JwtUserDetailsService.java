package com.nuevospa.desafiospringboot.service.impl;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nuevospa.desafiospringboot.model.User;
import com.nuevospa.desafiospringboot.repository.IUserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(String.format("User does not exist ", username));
        }
        User user = userOptional.get();
        
        UserDetails ud = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true, true, true, true, Collections.emptyList());
        return ud;
    }
}
