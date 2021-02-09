package com.nayker.chat.security;


import com.nayker.chat.error.KeyConverterException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
@ConfigurationPropertiesBinding
public class KeyConverter implements Converter<String, SecretKey>{

    @Override
    public SecretKey convert(String secretKey) throws KeyConverterException {
        try {
           return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        } catch (InvalidKeyException e) {
            throw new KeyConverterException("Password not convert");
        }
    }
}
