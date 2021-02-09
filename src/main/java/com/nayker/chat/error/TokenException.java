package com.nayker.chat.error;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class TokenException extends UsernameNotFoundException {

    public TokenException(String message) {
        super(message);
    }

    public TokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
