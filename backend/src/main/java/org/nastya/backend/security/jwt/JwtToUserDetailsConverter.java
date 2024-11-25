package org.nastya.backend.security.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.nastya.backend.model.User;
import org.nastya.backend.security.CustomUserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtToUserDetailsConverter {

    public CustomUserDetails convert(DecodedJWT jwt) {
        return CustomUserDetails.builder()
                .id(Integer.valueOf(jwt.getSubject()))
                .username(jwt.getClaim("n").asString())
                .email(jwt.getClaim("e").asString())
                .build();
    }
}
