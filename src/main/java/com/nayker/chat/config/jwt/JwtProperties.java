package com.nayker.chat.config.jwt;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.crypto.SecretKey;
import java.time.Duration;

@Data
@Configuration
@ConfigurationProperties(prefix = "security.jwt")
@Validated
public class JwtProperties {

    private Duration expiredTime = Duration.ofDays(1);
    private SecretKey secretKey;
}


