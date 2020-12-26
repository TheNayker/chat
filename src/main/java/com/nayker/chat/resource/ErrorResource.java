package com.nayker.chat.resource;

import lombok.Data;
import lombok.NonNull;

@Data
public class ErrorResource {
    @NonNull
    private String message;
}
