package com.nayker.chat.service;

import com.nayker.chat.dto.Jwt;
import com.nayker.chat.dto.User;
import com.nayker.chat.entity.UserEntity;
import com.nayker.chat.form.AuthenticationRequest;

import java.util.Optional;

public interface UserService {
     User addUser(String username, String password);

     Jwt loginUser(String username, String password);

     Optional<UserEntity> findByUsername(String username);
}
