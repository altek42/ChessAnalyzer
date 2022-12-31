package pl.altek.analyzer.auth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenManager {
    private static final Logger log = LoggerFactory.getLogger(JwtTokenManager.class);

    private final Integer JWT_EXPIRATION_MS = 900000;
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(key).build();

    public JwtTokenManager() {
    }

    public String getJwtFromRequest(HttpServletRequest request) {
        final String authorizationToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.isEmpty(authorizationToken) || !authorizationToken.startsWith("Bearer ")) {
            return null;
        }
        return authorizationToken.split(" ")[1].trim();
    }

    public JwtToken decodeJwt(String token) {
        Claims body = parseClaimsJws(token);
        if (null == body) {
            return null;
        }

        JwtToken jwtToken = new JwtToken();
        jwtToken.setUserId(body.getSubject());
        return jwtToken;
    }

    private Claims parseClaimsJws(String token) {
        try {
            return jwtParser.parseClaimsJws(token).getBody();
        } catch (SignatureException ex) {
            log.info("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.info("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.info("Token expired");
        } catch (UnsupportedJwtException ex) {
            log.info("UnsupportedJwtException");
        } catch (IllegalArgumentException ex) {
            log.info(ex.getMessage());
        }
        return null;
    }

    public String encodeTokenJwt(JwtToken jwtToken) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION_MS);

        return Jwts.builder()
                .setSubject(jwtToken.getUserId().toString())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key)
                .compact();
    }

}
