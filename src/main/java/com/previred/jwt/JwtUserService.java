package com.previred.jwt;


import com.previred.model.dao.UsuarioDao;
import com.previred.model.entitys.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserService implements UserDetailsService{

    final UsuarioDao usuarioDao;

    public JwtUserService(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByMail(username.toUpperCase());
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("USER_ROLE"));
        return new User(usuario.getMail(), new BCryptPasswordEncoder().encode(usuario.getPassword()), authorityList);
    }

}
