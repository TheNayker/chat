package com.nayker.chat.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class Jwt {

    @NonNull
    private String token;
}
