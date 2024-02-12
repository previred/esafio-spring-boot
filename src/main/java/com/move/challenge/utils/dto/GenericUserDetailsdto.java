package com.move.challenge.utils.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class GenericUserDetailsdto implements UserDetails {

   private final String username;
   private final String password;

   public GenericUserDetailsdto(String username, String password) {
      this.username = username;
      this.password = password;
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return null;
   }

   @Override
   public String getPassword() {
      return password;
   }

   @Override
   public String getUsername() {
      return username;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true; // La cuenta nunca expira
   }

   @Override
   public boolean isAccountNonLocked() {
      return true; // La cuenta nunca está bloqueada
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true; // Las credenciales nunca expiran
   }

   @Override
   public boolean isEnabled() {
      return true; // La cuenta siempre está habilitada
   }
}
