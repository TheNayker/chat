package com.nayker.chat.resource;

import com.nayker.chat.dto.Jwt;
import lombok.Data;

@Data
public class AuthenticatedResource {
    private String token;

    public static AuthenticatedResource fromDto(Jwt jwt) {
        var resource = new AuthenticatedResource();
        resource.setToken(jwt.getToken());

        return resource;
    }
}
