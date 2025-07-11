package br.com.forumhub.aluraOne.security;

import com.auth0.jwt.JWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC256;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    public String generateToken(UserDetails userDetails){
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withIssuedAt(new Date())
                .withExpiresAt(new java.util.Date(System.currentTimeMillis() + jwtExpiration))
                .sign(HMAC256(jwtSecret));
    }

    public String getEmailFromToken(String token) {
        return JWT.require(HMAC256(jwtSecret))
                .build()
                .verify(token)
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            JWT.require(HMAC256(jwtSecret)).build().verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
