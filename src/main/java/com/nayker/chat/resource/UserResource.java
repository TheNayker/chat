package com.nayker.chat.resource;

import com.nayker.chat.dto.User;
import lombok.Data;

@Data
public class UserResource {
    private Long id;
    private String username;

    public static UserResource fromDto(User user) {
        var resource = new UserResource();
        resource.setId(user.getId());
        resource.setUsername(user.getUsername());

        return resource;
    }
}
