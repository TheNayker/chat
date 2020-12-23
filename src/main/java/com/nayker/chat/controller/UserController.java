package com.nayker.chat.controller;


import com.nayker.chat.dto.User;
import com.nayker.chat.form.UserRequest;
import com.nayker.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/chat/users")
public class UserController {

    private final UserService service ;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<User> getUsers() {
        return service.getUsers();
    }

    @PostMapping("/registration")
    @ResponseStatus(code = HttpStatus.CREATED)
    public User addUser(
        @Valid @RequestBody UserRequest request) {
        return service.addUsers(request.getUsername(), request.getEmail(), request.getPassword());
    }
}

