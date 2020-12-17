package com.nayker.chat.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class MessageRequest {
    @NotBlank(message = "The name cannot be empty")
    @Size(min = 1, max = 30)
    private String name;
    @NotBlank(message = "The content cannot be empty")
    @Size(min = 1, max = 300)
    private String content;
}
