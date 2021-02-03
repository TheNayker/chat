package com.nayker.chat.security;


import com.nayker.chat.config.jwt.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final SecretKey secretKey;
    private final Duration validity;
    private final JwtParser jwtParser;


    public JwtTokenProvider(JwtProperties jwtProperties, JwtParser jwtParser) {
        this.secretKey = jwtProperties.getSecretKey();
        this.validity = jwtProperties.getExpiredTime();
        this.jwtParser = jwtParser;
    }

    public String createToken(String username) {
        Claims claims = Jwts.claims().setSubject(username);
        Date now = new Date();
        Date validity = new Date(now.getTime() + this.validity.toMillis());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsername(String token) {
        return jwtParser.setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }
}
