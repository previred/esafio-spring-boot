package cl.dpichinil.desafio.desafiospringboot.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties("msg")
public class MessageProperties {
    private Map<Integer, String> getUserByUsername;
    private Map<Integer, String> encodePassword;
    private Map<Integer, String> login;
    private Map<Integer, String> logged;

    public Map<Integer, String> getGetUserByUsername() {
        return getUserByUsername;
    }

    public void setGetUserByUsername(Map<Integer, String> getUserByUsername) {
        this.getUserByUsername = getUserByUsername;
    }

    public Map<Integer, String> getEncodePassword() {
        return encodePassword;
    }

    public void setEncodePassword(Map<Integer, String> encodePassword) {
        this.encodePassword = encodePassword;
    }

    public Map<Integer, String> getLogin() {
        return login;
    }

    public void setLogin(Map<Integer, String> login) {
        this.login = login;
    }

    public Map<Integer, String> getLogged() {
        return logged;
    }

    public void setLogged(Map<Integer, String> logged) {
        this.logged = logged;
    }
}
