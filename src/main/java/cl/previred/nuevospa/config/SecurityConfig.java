package cl.previred.nuevospa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cl.previred.nuevospa.config.filters.JwtTokenFilter;

@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        String[] publicUrl = {
            "/api/auth/login",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/webjars/**",
            "/h2-console/**"
        };
        http
            .csrf(csrf -> csrf.disable())
            .addFilterAfter(new JwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests(requests -> requests
                .antMatchers(publicUrl).permitAll()
                .anyRequest().authenticated()
            ).httpBasic(basic -> basic.disable())
            .headers(headers -> headers.frameOptions().disable());
        return http.build();
    }
}
