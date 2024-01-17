package cl.previred.challenge.config;

import cl.previred.challenge.config.security.JwtUserDataArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@Profile({"local"})
public class WebConfigurer implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(jwtUserDataArgumentResolver());
    }

    @Bean
    public JwtUserDataArgumentResolver jwtUserDataArgumentResolver() {
        return new JwtUserDataArgumentResolver();
    }


}
