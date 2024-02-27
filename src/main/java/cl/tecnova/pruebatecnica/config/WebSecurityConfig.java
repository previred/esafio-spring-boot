package cl.tecnova.pruebatecnica.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Arrays;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    @Autowired
    private JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        AntPathRequestMatcher[] requestMatcher = Arrays.stream(SecurityConstans.AUTH_WHITELIST)
                .map(AntPathRequestMatcher::new)
                .toArray(AntPathRequestMatcher[]::new);

        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz -> authz.requestMatchers(requestMatcher).permitAll().anyRequest().authenticated())
                .addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
