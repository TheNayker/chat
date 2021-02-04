package com.nayker.chat.config;


import org.passay.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class ValidatorConfig {

    @Bean
    public PasswordValidator PasswordValidator() {
        return new PasswordValidator(Arrays.asList(
                new LengthRule(15, 35),
                new CharacterRule(EnglishCharacterData.UpperCase, 2),
                new CharacterRule(EnglishCharacterData.LowerCase, 2),
                new CharacterRule(EnglishCharacterData.Digit, 2),
                new CharacterRule(EnglishCharacterData.Special, 2),
                new WhitespaceRule()));
    }
}
