package com.moveapps.taskmanager.security;

import com.moveapps.taskmanager.entity.User;
import com.moveapps.taskmanager.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private static final String ADMIN_USER = "root";
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUser(username);
        return
            org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(getRandomRole(user.getUsername()).toArray(new String[0]))
                .build();
    }


    private List<String> getRandomRole(String user){
        if(ADMIN_USER.equals(user)){
            return List.of("ADMIN");
        }else{
            return List.of("USER");
        }
    }
}
