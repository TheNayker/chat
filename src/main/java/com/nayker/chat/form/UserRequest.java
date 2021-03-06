package com.nayker.chat.form;

import com.nayker.chat.validator.PasswordConstrain;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserRequest {
    @NotBlank(message = "The name cannot be empty")
    private String username;
    @PasswordConstrain
    private String password;
}
