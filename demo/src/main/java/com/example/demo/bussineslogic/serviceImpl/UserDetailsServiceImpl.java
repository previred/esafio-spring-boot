package com.example.demo.bussineslogic.serviceImpl;

import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository oUserRepository;

    private static UserEntity oUserEntity;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        this.oUserEntity = oUserRepository.findOneByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe."));

        return new User(this.oUserEntity.getUsername(),
                this.oUserEntity.getPassword(),
                true,
                true,
                true,
                true,
                Collections.emptyList());
    }

    public static UserEntity getoUserEntity() {
        return oUserEntity;
    }
}
