package cl.previred.challenge.config.security;

import cl.previred.challenge.repository.entity.UserPassword;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class SecureUserDetails implements UserDetails {

    private final transient UserPassword userPassword;

    public SecureUserDetails(UserPassword password) {
        this.userPassword = password;
    }

    @Override
    public String getPassword() {
        return userPassword.getPassword();
    }

    @Override
    public String getUsername() {
        return userPassword.getUser().getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userPassword.getUser().getRole());
        return new ArrayList<>(Collections.singletonList(authority));
    }

    public String getRol(){
        return userPassword.getUser().getRole();
    }

}
