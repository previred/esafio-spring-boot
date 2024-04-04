package co.moveapps.spa.core.config;

import co.moveapps.spa.core.config.security.JwtAuthorizationRequestFilter;
import co.moveapps.spa.core.config.security.JwtUnAuthorizedEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author andresduran0205@gmail.com
 */

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtUnAuthorizedEntryPoint jwtUnAuthorizedEntryPoint;
    private final JwtAuthorizationRequestFilter jwtAuthorizationRequestFilter;

    public SecurityConfig(JwtUnAuthorizedEntryPoint jwtUnAuthorizedEntryPoint,
                          JwtAuthorizationRequestFilter jwtAuthorizationRequestFilter) {
        this.jwtUnAuthorizedEntryPoint = jwtUnAuthorizedEntryPoint;
        this.jwtAuthorizationRequestFilter = jwtAuthorizationRequestFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) // Disabling CSRF as we use JWT which is immune to CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(new AntPathRequestMatcher("/auth")).permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthorizationRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
