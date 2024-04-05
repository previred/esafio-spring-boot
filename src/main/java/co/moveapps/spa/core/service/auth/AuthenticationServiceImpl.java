package co.moveapps.spa.core.service.auth;

import co.moveapps.spa.core.config.security.dto.CurrentUserPrincipal;
import co.moveapps.spa.core.constants.MessageResponseConstant;
import co.moveapps.spa.core.constants.TechnicalMessageResponseConstant;
import co.moveapps.spa.core.controller.model.CredentialsResponse;
import co.moveapps.spa.core.exception.BusinessException;
import co.moveapps.spa.core.model.entity.AuthUserEntity;
import co.moveapps.spa.core.model.repository.IAuthUserEntityRepository;
import co.moveapps.spa.core.utility.JsonCommonUtility;
import co.moveapps.spa.core.utility.SecurityCommonUtility;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final String JWT_CLAIM_METADATA_REQUIRED = "metadata-id";

    @Value("${auth.access.expiration-minutes}")
    private Long JWT_ACCESS_TOKEN_EXPIRATION_TIME_MINUTES;
    @Value("${auth.access.secret}")
    private String JWT_ACCESS_TOKEN_SECRET_KEY;


    private final IAuthUserEntityRepository iAuthUserEntityRepository;

    public AuthenticationServiceImpl(IAuthUserEntityRepository iAuthUserEntityRepository) {
        this.iAuthUserEntityRepository = iAuthUserEntityRepository;
    }

    @Override
    public CredentialsResponse authentication(String username, String password) throws BusinessException {
        if (!SecurityCommonUtility.getInstance().isSanitized(username))
            throw new BusinessException(TechnicalMessageResponseConstant.EXCEPTION_BAD_REQUEST_VALIDATE_STRING_SANITIZE);
        if (!SecurityCommonUtility.getInstance().isSanitized(password))
            throw new BusinessException(TechnicalMessageResponseConstant.EXCEPTION_BAD_REQUEST_VALIDATE_STRING_SANITIZE);

        final String passwordEncode = SecurityCommonUtility.getInstance().encode(password);
        AuthUserEntity authUserEntity = iAuthUserEntityRepository.findByUsernameAndPassword(username, passwordEncode)
                .orElseThrow(() -> new BusinessException(MessageResponseConstant.EXCEPTION_AUTHENTICATION_USER_FAILED));

        CurrentUserPrincipal principal = CurrentUserPrincipal.builder()
                .sessionId(authUserEntity.getId())
                .build();

        final Map<String, Object> claims = new HashMap<>();
        final String metadata = JsonCommonUtility.getInstance().write(principal);
        claims.put(JWT_CLAIM_METADATA_REQUIRED, Base64.getEncoder().encode(metadata.getBytes()));

        final String token = SecurityCommonUtility.getInstance().generateJWT(claims, JWT_ACCESS_TOKEN_SECRET_KEY, JWT_ACCESS_TOKEN_EXPIRATION_TIME_MINUTES);

        authUserEntity.setLastTokenDate(LocalDateTime.now());
        iAuthUserEntityRepository.save(authUserEntity);

        return new CredentialsResponse(token, (int) (JWT_ACCESS_TOKEN_EXPIRATION_TIME_MINUTES * 60));
    }

    @Override
    public CurrentUserPrincipal validate(String token) throws BusinessException {
        if (!SecurityCommonUtility.getInstance().isSanitized(token))
            throw new BusinessException(TechnicalMessageResponseConstant.EXCEPTION_BAD_REQUEST_VALIDATE_STRING_SANITIZE);

        try {
            Claims claims = SecurityCommonUtility.getInstance().verifyJWT(token, JWT_ACCESS_TOKEN_SECRET_KEY);
            String claim_metadata = (String) claims.get(JWT_CLAIM_METADATA_REQUIRED);
            String metadata = new String(Base64.getDecoder().decode(claim_metadata));
            return JsonCommonUtility.getInstance().read(new String(Base64.getDecoder().decode(metadata)), CurrentUserPrincipal.class);
        } catch (Exception exception) {
            log.error("JWT WRONG, PLEASE CHECK THE TOKEN - {}", exception.getMessage());
        }
        return null;
    }
}
