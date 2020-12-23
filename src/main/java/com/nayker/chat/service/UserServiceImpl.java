package com.nayker.chat.service;

import com.nayker.chat.dto.User;
import com.nayker.chat.repository.UserRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Cacheable("users")
    public List<User> getUsers() {
        return repository.findAll()
                .stream()
                .map(User::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @CacheEvict(value = "users", allEntries = true, beforeInvocation = true)
    public User addUsers(String username, String email, String password)
            throws UsernameNotFoundException {
        var user = repository.findByUsername(username);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);

        return User.fromEntity(user);
    }

}
