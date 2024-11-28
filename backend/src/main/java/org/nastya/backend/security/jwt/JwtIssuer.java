package org.nastya.backend.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor
public class JwtIssuer {

    private final JwtProperties jwtProperties;

    public String issue(Integer userId, String username, String email){
        return JWT.create()
                .withSubject(String.valueOf(userId))
                .withExpiresAt(Instant.now().plus(Duration.of(1, ChronoUnit.HOURS)))
                .withClaim("e", email)
                .withClaim("n", username)
                .sign(Algorithm.HMAC256(jwtProperties.getSecretKey()));
    }
}
