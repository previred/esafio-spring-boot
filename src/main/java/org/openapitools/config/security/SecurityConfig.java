package org.openapitools.config.security;

import lombok.RequiredArgsConstructor;
import org.openapitools.config.AuthProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("http://localhost:4200")
    private String origin;

    private final AuthProperties authProperties;

    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.httpBasic().disable();
        http.formLogin().disable();
        http.csrf().ignoringAntMatchers("/**");
        http.addFilterBefore(JwtFilter.from(authProperties.getSecret()), UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().mvcMatchers(
                "/login",
                "/v2/api-docs",
                "/swagger-ui.html",
                "/swagger-ui/**"
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

