package com.nayker.chat.service;

import com.nayker.chat.dto.User;
import com.nayker.chat.entity.UserEntity;
import com.nayker.chat.form.AuthenticationRequest;
import com.nayker.chat.resource.AuthenticatedResource;

import java.util.Optional;

public interface UserService {
     User addUser(String username, String password);

     AuthenticatedResource loginUser(AuthenticationRequest request);

     Optional<UserEntity> findByUsername(String username);
}
