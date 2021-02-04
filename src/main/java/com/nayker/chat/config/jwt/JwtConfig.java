package com.nayker.chat.config.jwt;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class JwtConfig {
    private final SecretKey secretKey;

    public JwtConfig(JwtProperties jwtProperties) {
        this.secretKey = jwtProperties.getSecretKey();
    }

    @Bean
    public JwtParser jwtParser() {
        return Jwts.parser().setSigningKey(secretKey);
    }
}
