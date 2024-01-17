package cl.previred.challenge.config.security;

import cl.previred.challenge.repository.UserPasswordRepository;
import cl.previred.challenge.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AppUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppUserDetailsService.class);

    private final UserRepository userRepository;
    private final UserPasswordRepository userPasswordRepository;

    public AppUserDetailsService(UserRepository userRepository, UserPasswordRepository userPasswordRepository) {
        this.userRepository = userRepository;
        this.userPasswordRepository = userPasswordRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .flatMap(user -> userPasswordRepository.findByUserId(user.getId()))
                .map(SecureUserDetails::new)
                .orElseThrow(()->new UsernameNotFoundException("User not found"));

    }


}
