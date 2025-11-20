package com.appGastroCol.product_backend.security;

import java.util.Date;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.userdetails.UserDetails;

@Component
public class JwtUtil {

    private final String SECRET = "YOUR_SECRET_KEY_VERY_SECRET";
    private final long EXPIRATION_MS = 1000 * 60 * 60 * 24;

    private Algorithm algorithm() {
        return Algorithm.HMAC256(SECRET);
    }

    public String generateToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .sign(algorithm());
    }

    public String getUsernameFromToken(String token) {
        return getDecoded(token).getSubject();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return getDecoded(token).getExpiresAt().before(new Date());
    }

    private DecodedJWT getDecoded(String token) {
        return JWT.require(algorithm()).build().verify(token);
    }
}
