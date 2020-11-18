package com.nayker.chat.entity;

import lombok.Data;

@Data
public class DictionaryWordRequest {

    @NotBlank(message = "the word can't be blank")
    public String word;
}
