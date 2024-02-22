package cl.dpichinil.desafio.desafiospringboot.util;

import cl.dpichinil.desafio.desafiospringboot.dto.JwtUser;
import cl.dpichinil.desafio.desafiospringboot.persistence.entity.User;
import jakarta.servlet.http.HttpServletRequest;

public class SecurityFunction {
    public static String getHashText(String username, String password) {
        return String.format("%s:%s", username, password);
    }

    public static String getHeaderToken(HttpServletRequest request){
        return request.getHeader(SecurityConstant.HEADER_TOKEN);
    }
    public static String quitaBarer(String authHeader){
        return authHeader.substring(7);
    }

    public static String getToken(HttpServletRequest request){
        String authHeader = getHeaderToken(request);
        return quitaBarer(authHeader);
    }

    public static JwtUser parseUserToJwtUser(User user){
        return new JwtUser(
                user.getUsername(),
                user.getPassword(),
                user.getAuthoritiesText(),
                user.isExpired(),
                user.isLocked(),
                user.isExpired(),
                user.isActive()
        );
    }

}
