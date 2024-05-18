package co.com.task.api.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.com.task.api.domain.User;
import co.com.task.api.repository.UserRepository;

@Service
public class UserSecurityDetailsService implements UserDetailsService {
    
    private UserRepository userRepository;

    public UserSecurityDetailsService(UserRepository userRepository) {
	this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	Optional<User> userBD = userRepository.getUserByEmail(username);

	if(userBD.isPresent()) {
	    org.springframework.security.core.userdetails.User.UserBuilder userBuilder = org.springframework.security.core.userdetails.User
		    .withUsername(username);
	    userBuilder.password(userBD.get().getPassword()).roles("USER");
	    return userBuilder.build();
	}else {
	    throw new UsernameNotFoundException(username);
	}
    }
}
