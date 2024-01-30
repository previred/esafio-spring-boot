package cl.nuevo.spa.desafio.configuration;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class prueba {

    public static void main(String[] args) {
        String password = "contraseñaseguraelena";
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println("Hashed Password: " + hashedPassword);

    }
}
