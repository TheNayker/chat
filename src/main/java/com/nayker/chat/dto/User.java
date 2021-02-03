package com.nayker.chat.dto;

import com.nayker.chat.entity.UserEntity;
import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String password;

    public static User fromEntity(UserEntity entity) {
        User user = new User();
        user.setId(entity.getId());
        user.setUsername(entity.getUsername());
        user.setPassword(entity.getPassword());

        return user;
    }
}
