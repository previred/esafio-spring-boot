package com.desafio.desafiospringboot.model.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import com.desafio.desafiospringboot.model.auth.filter.JwtValidationFilter;

@Configuration
public class SpringSecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> auth
                        .antMatchers("/h2-console/**","/doc/**","/login","/swagger-ui/**","/v3/**","/","/api/**","/v2/**","/swagger-ui.html")
                        .permitAll()
                        .anyRequest().authenticated()  // Requiere autenticación para cualquier otra solicitud
                )
                .addFilter(new JwtValidationFilter(authenticationManager()))
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"))
                        .ignoringRequestMatchers(new AntPathRequestMatcher("/login"))
                        .ignoringRequestMatchers(new AntPathRequestMatcher("/doc/**"))
                        .ignoringRequestMatchers(new AntPathRequestMatcher("/swagger-ui/**"))
                        .ignoringRequestMatchers(new AntPathRequestMatcher("/v3/api-docs/**")))
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.csrf().disable();
        http.headers().frameOptions().disable();

        return http.build();
    }
}
