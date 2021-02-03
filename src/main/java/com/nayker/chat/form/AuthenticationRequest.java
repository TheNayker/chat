package com.nayker.chat.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AuthenticationRequest {
    @NotBlank(message = "The name cannot be empty")
    private String username;
    @NotBlank(message = "The password cannot be empty")
    private String password;
}
