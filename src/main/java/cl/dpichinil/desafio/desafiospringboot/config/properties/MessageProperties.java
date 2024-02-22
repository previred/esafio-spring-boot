package cl.dpichinil.desafio.desafiospringboot.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties("msg")
public class MessageProperties {
    private Map<Integer, String> getUserByUsername;

    public Map<Integer, String> getGetUserByUsername() {
        return getUserByUsername;
    }

    public void setGetUserByUsername(Map<Integer, String> getUserByUsername) {
        this.getUserByUsername = getUserByUsername;
    }
}
