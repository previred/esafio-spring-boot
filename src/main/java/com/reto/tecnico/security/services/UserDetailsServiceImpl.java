package com.reto.tecnico.security.services;

import com.reto.tecnico.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final IUserService userService;

  public UserDetailsServiceImpl(IUserService userService){
    this.userService = userService;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    com.reto.tecnico.model.User user = getUserDetails(email);

    if (user == null) {
      throw new UsernameNotFoundException(email);
    }
    return User
            .withUsername(email)
            .password(user.getPassword())
            .roles("ADMIN")
            .build();
  }

  private com.reto.tecnico.model.User getUserDetails(String username) {
    com.reto.tecnico.model.User user = null;
    try {
      user = userService.getUserByUsername(username);
    } catch (Exception error) {
      throw new UsernameNotFoundException("Usuario "+ username + " no encontrado");
    }

    return List.of(user)
            .stream()
            .filter(e -> e != null && e.getUsername().equals(username))
            .findFirst()
            .orElse(null);
  }
}
