package com.nayker.chat.resource;

import lombok.Data;

@Data
public class AuthenticatedResource {
    private String token;

    public AuthenticatedResource(String token) {
        this.token = token;
    }
}
