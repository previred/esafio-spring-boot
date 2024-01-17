package cl.previred.challenge.config;

import cl.previred.challenge.config.security.ApiAuthenticationEntryPoint;
import cl.previred.challenge.config.security.AppUserDetailsService;
import cl.previred.challenge.config.security.JwtAuthenticationFilter;
import cl.previred.challenge.config.security.JwtManager;
import cl.previred.challenge.repository.UserPasswordRepository;
import cl.previred.challenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
@Profile("local")
public class WebSecurity {

    @Autowired
    private JwtManager jwtManager;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    protected AuthenticationEntryPoint authenticationEntryPoint() {
        return new ApiAuthenticationEntryPoint();
    }

    @Bean
    protected Filter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtManager);//fixme
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrfCustomizer -> csrfCustomizer
                        .ignoringAntMatchers("/**"))
                .headers(headersCustomizer -> headersCustomizer
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .exceptionHandling(exceptionHandlingCustomizer -> exceptionHandlingCustomizer
                        .authenticationEntryPoint(authenticationEntryPoint()))
                .authorizeRequests(expressionInterceptUrlRegistry -> {
                    expressionInterceptUrlRegistry.antMatchers("/auth/**").permitAll();
                    expressionInterceptUrlRegistry.antMatchers("/h2-console/**").permitAll();
                    expressionInterceptUrlRegistry.antMatchers("/v2/**").permitAll();
                    expressionInterceptUrlRegistry.antMatchers("/swagger-ui.html").permitAll();
                    expressionInterceptUrlRegistry.antMatchers("/swagger-ui/**").permitAll();
                    expressionInterceptUrlRegistry.antMatchers("/swagger-resources/**").permitAll();
                    expressionInterceptUrlRegistry.antMatchers("/webjars/**").permitAll();
                    expressionInterceptUrlRegistry.antMatchers("/api-docs/**").permitAll();
                    expressionInterceptUrlRegistry.anyRequest().authenticated();
                })
                .sessionManagement(sessionManagementCustomizer -> sessionManagementCustomizer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter(), BasicAuthenticationFilter.class)
                .authenticationManager(authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)));

        return http.build();
    }

    @Bean
    protected UserDetailsService userDetailsService(UserRepository userRepository, UserPasswordRepository userPasswordRepository) {
        return new AppUserDetailsService(userRepository, userPasswordRepository);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123"));
    }
}
