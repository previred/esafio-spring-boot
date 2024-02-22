package cl.dpichinil.desafio.desafiospringboot.config.security;

import cl.dpichinil.desafio.desafiospringboot.dto.JwtUser;
import cl.dpichinil.desafio.desafiospringboot.persistence.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import cl.dpichinil.desafio.desafiospringboot.persistence.repository.UserRepository;
import cl.dpichinil.desafio.desafiospringboot.util.SecurityFunction;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        String hashText = SecurityFunction.getHashText(username, password);
        Optional<User> optional = userRepository.findByUsername(username);
        if(optional.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        User user = optional.get();
        String matchDb = user.getPassword();
        if(!passwordEncoder.matches(hashText, matchDb)){
            throw new AuthenticationException("Invalid credentials") {};
        }
        JwtUser jwtUser = SecurityFunction.parseUserToJwtUser(user);
        Authentication authenticated = new UsernamePasswordAuthenticationToken(
                jwtUser, password, jwtUser.getAuthorities());
        return authenticated;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
