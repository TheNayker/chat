package com.nayker.chat.service;

import com.nayker.chat.dto.User;
import com.nayker.chat.entity.UserEntity;
import com.nayker.chat.error.AuthException;
import com.nayker.chat.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User addUser(String username, String email, String password) {
        if (repository.existsByUsername(username)) {
            throw new AuthException("Username already exists");
        }

        var user = new UserEntity();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);

        return User.fromEntity(user);
    }

}
