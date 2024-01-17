package cl.previred.challenge.config.security;

public record JwtUserData(String username, String rol) {

    @Override
    public String toString() {
        return "JwtUserData{" +
                "username='" + username + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}
