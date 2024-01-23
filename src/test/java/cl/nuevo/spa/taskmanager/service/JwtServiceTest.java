package cl.nuevo.spa.taskmanager.service;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.anyMap;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultJwtBuilder;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.MacAlgorithm;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class JwtServiceTest {

  public static final String SECRET_KEY = "SECRET_KEY";
  public static final String EXPIRATION_IN_MINUTES = "EXPIRATION_IN_MINUTES";
  public static final String SECRET_KEY_VALUE = "value";
  public static final String USER_NAME = "USER_NAME";
  public static final String JWT_SIGN = "JWT_SIGN";
  public static final String JWT_DECODE = "JWT_DECODE";
  private static final int EXPIRATION_IN_MINUTES_VALUE = 10;
  private static final String MAC_ALGORITHM = "macAlgorithm";
  @InjectMocks JwtService jwtService;

  private static void verifyInteractions(
      MockedStatic<Jwts> jwtsMockedStatic,
      DefaultJwtBuilder defaultJwtParserBuilder,
      JwtBuilder.BuilderHeader builderHeader) {
    jwtsMockedStatic.verify(Jwts::builder, times(1));
    verify(defaultJwtParserBuilder, times(1)).header();
    verify(builderHeader, times(1)).type(anyString());
    verify(defaultJwtParserBuilder, times(1)).subject(anyString());
    verify(defaultJwtParserBuilder, times(1)).issuedAt(any(Date.class));
    verify(defaultJwtParserBuilder, times(1))
        .signWith(any(SecretKey.class), any(MacAlgorithm.class));
    verify(defaultJwtParserBuilder, times(1)).compact();
  }

  private static void configureStaticMocks(
      DefaultJwtBuilder defaultJwtBuilder, JwtBuilder.BuilderHeader builderHeader) {

    when(builderHeader.and()).thenReturn(defaultJwtBuilder);
    when(builderHeader.type(anyString())).thenReturn(builderHeader);
    when(defaultJwtBuilder.header()).thenReturn(builderHeader);
    when(defaultJwtBuilder.subject(anyString())).thenReturn(defaultJwtBuilder);
    when(defaultJwtBuilder.subject(anyString())).thenReturn(defaultJwtBuilder);
    when(defaultJwtBuilder.issuedAt(any(Date.class))).thenReturn(defaultJwtBuilder);
    when(defaultJwtBuilder.expiration(any(Date.class))).thenReturn(defaultJwtBuilder);
    when(defaultJwtBuilder.claims(anyMap())).thenReturn(defaultJwtBuilder);
    when(defaultJwtBuilder.claims(anyMap())).thenReturn(defaultJwtBuilder);
    when(defaultJwtBuilder.signWith(any(SecretKey.class), any(MacAlgorithm.class)))
        .thenReturn(defaultJwtBuilder);
    when(defaultJwtBuilder.compact()).thenReturn(JWT_SIGN);
  }

  @Test
  void ShouldGeneratedJwtSigned() {
    Instant instantMock = mock(Instant.class);
    when(instantMock.toEpochMilli()).thenReturn(123456789L);
    when(instantMock.plusSeconds(anyLong())).thenReturn(instantMock);
    try (MockedStatic<Instant> instantMockedStatic = mockStatic(Instant.class);
        MockedStatic<Jwts> jwtsMockedStatic = mockStatic(Jwts.class);
        MockedStatic<Keys> keysMockedStatic = mockStatic(Keys.class)) {
      SecretKey secretKey = mock(SecretKey.class);
      keysMockedStatic.when(() -> Keys.hmacShaKeyFor(any(byte[].class))).thenReturn(secretKey);
      instantMockedStatic.when(Instant::now).thenReturn(instantMock);
      DefaultJwtBuilder defaultJwtParserBuilder = mock(DefaultJwtBuilder.class);
      JwtBuilder.BuilderHeader builderHeader = mock(JwtBuilder.BuilderHeader.class);
      jwtsMockedStatic.when(Jwts::builder).thenReturn(defaultJwtParserBuilder);
      configureStaticMocks(defaultJwtParserBuilder, builderHeader);

      UserDetails userDetails = mock(UserDetails.class);
      when(userDetails.getUsername()).thenReturn(USER_NAME);

      Map<String, Object> extraClaims = mock(Map.class);

      ReflectionTestUtils.setField(jwtService, MAC_ALGORITHM, Jwts.SIG.HS256);
      ReflectionTestUtils.setField(jwtService, SECRET_KEY, SECRET_KEY_VALUE);
      ReflectionTestUtils.setField(jwtService, EXPIRATION_IN_MINUTES, EXPIRATION_IN_MINUTES_VALUE);

      String actual = jwtService.generateToken(userDetails, extraClaims);

      Assertions.assertEquals(JWT_SIGN, actual, "should return JWT Signed");

      verifyInteractions(jwtsMockedStatic, defaultJwtParserBuilder, builderHeader);
    }
  }

  @Test
  void shouldExtractUserName() {
    try (MockedStatic<Jwts> jwtsMockedStatic = mockStatic(Jwts.class);
        MockedStatic<Keys> keysMockedStatic = mockStatic(Keys.class)) {

      SecretKey secretKey = mock(SecretKey.class);
      keysMockedStatic.when(() -> Keys.hmacShaKeyFor(any(byte[].class))).thenReturn(secretKey);
      DefaultJwtBuilder defaultJwtBuilder = mock(DefaultJwtBuilder.class);
      JwtParserBuilder jwtParserBuilder = mock(JwtParserBuilder.class);
      JwtParser JwtParser = mock(JwtParser.class);
      jwtsMockedStatic.when(Jwts::builder).thenReturn(defaultJwtBuilder);
      jwtsMockedStatic.when(Jwts::parser).thenReturn(jwtParserBuilder);
      when(jwtParserBuilder.verifyWith(any(SecretKey.class))).thenReturn(jwtParserBuilder);
      when(jwtParserBuilder.build()).thenReturn(JwtParser);
      Jws<Claims> claimsJws = mock(Jws.class);
      Claims claims = mock(Claims.class);
      when(JwtParser.parseSignedClaims(JWT_SIGN)).thenReturn(claimsJws);
      when(claimsJws.getPayload()).thenReturn(claims);
      when(claims.getSubject()).thenReturn(USER_NAME);
      ReflectionTestUtils.setField(jwtService, SECRET_KEY, SECRET_KEY_VALUE);

      String actual = jwtService.extractUserName(JWT_SIGN);

      Assertions.assertEquals(USER_NAME, actual);
    }
  }
}
