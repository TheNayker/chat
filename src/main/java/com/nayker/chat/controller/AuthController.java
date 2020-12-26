package com.nayker.chat.controller;


import com.nayker.chat.error.AuthException;
import com.nayker.chat.form.UserRequest;
import com.nayker.chat.resource.ErrorResource;
import com.nayker.chat.resource.UserResource;
import com.nayker.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService service;

    @Autowired
    public AuthController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserResource register(@Valid @RequestBody UserRequest request) {
        return UserResource.fromDto(
                service.addUser(request.getUsername(), request.getEmail(), request.getPassword())
        );
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResource> handleAuthException(AuthException exception) {
        return new ResponseEntity<>(
                new ErrorResource(exception.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }
}
