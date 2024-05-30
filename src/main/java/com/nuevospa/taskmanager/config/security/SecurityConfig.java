package com.nuevospa.taskmanager.config.security;

import lombok.*;
import com.nuevospa.taskmanager.config.AuthProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.*;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("http://localhost:4200")
    private String origin;

    private final AuthProperties authProperties;

    private final AccessDeniedHandler customAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.httpBasic().disable();
        http.formLogin().disable();
        http.csrf().ignoringAntMatchers("/**");
        http.addFilterBefore(JwtAuthFilter.from(authProperties.getSecret()), UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().mvcMatchers(
                "/**"
        );
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin(origin);
        config.setAllowedMethods(Arrays.asList("POST", "OPTIONS", "GET", "DELETE", "PUT"));
        config.setAllowedHeaders(Arrays.asList(
                "Origin",
                "Content-Type",
                "Accept",
                "Authorization",
                "User-Key",
                "Request-Tracker",
                "Session-Tracker",
                "X-XSRF-TOKEN",
                "X-IBM-CLIENT-ID",
                "X-IBM-CLIENT-SECRET",
                "Message-ID"
        ));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}

