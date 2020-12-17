package com.nayker.chat.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class DictionaryWordRequest {
    @NotBlank(message = "The word can't be blank")
    @Size(min = 1, max = 15)
    private String word;
}
