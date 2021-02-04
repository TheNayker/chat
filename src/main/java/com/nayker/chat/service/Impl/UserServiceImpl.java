package com.nayker.chat.service.Impl;

import com.nayker.chat.dto.Jwt;
import com.nayker.chat.dto.User;
import com.nayker.chat.entity.UserEntity;
import com.nayker.chat.error.AuthException;
import com.nayker.chat.repository.UserRepository;
import com.nayker.chat.security.JwtTokenProvider;
import com.nayker.chat.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider provider;


    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder, JwtTokenProvider provider) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.provider = provider;
    }

    @Override
    public User addUser(String username, String password) {
        if (repository.existsByUsername(username)) {
            throw new AuthException("Username already exists");
        }

        var user = new UserEntity();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        repository.save(user);

        return User.fromEntity(user);
    }

    @Override
    public Jwt loginUser(String username, String password) throws AuthException {
        var user = repository.findByUsername(username).orElseThrow(() ->
                new AuthException("User not found"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new AuthException("Invalid password");
        }
        String token = provider.createToken(username);

        return new Jwt(token);
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
