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
    private Map<Integer, String> tareaCreate;
    private Map<Integer, String> tareaList;
    private Map<Integer, String> tareaDelete;
    private Map<Integer, String> tareaUpdate;
    private Map<Integer, String> estadoTareaList;

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

    public Map<Integer, String> getTareaCreate() {
        return tareaCreate;
    }

    public void setTareaCreate(Map<Integer, String> tareaCreate) {
        this.tareaCreate = tareaCreate;
    }

    public Map<Integer, String> getTareaList() {
        return tareaList;
    }

    public void setTareaList(Map<Integer, String> tareaList) {
        this.tareaList = tareaList;
    }

    public Map<Integer, String> getTareaDelete() {
        return tareaDelete;
    }

    public void setTareaDelete(Map<Integer, String> tareaDelete) {
        this.tareaDelete = tareaDelete;
    }

    public Map<Integer, String> getTareaUpdate() {
        return tareaUpdate;
    }

    public void setTareaUpdate(Map<Integer, String> tareaUpdate) {
        this.tareaUpdate = tareaUpdate;
    }

    public Map<Integer, String> getEstadoTareaList() {
        return estadoTareaList;
    }

    public void setEstadoTareaList(Map<Integer, String> estadoTareaList) {
        this.estadoTareaList = estadoTareaList;
    }
}
