package com.nayker.chat.dto;


import com.nayker.chat.entity.DictionaryWordEntity;
import lombok.Data;

@Data
public class Dictionary {

    private Long id;
    private String word;

    public static Dictionary fromEntity(DictionaryWordEntity entity) {
        Dictionary dictionary = new Dictionary();
        dictionary.setId(entity.getId());
        dictionary.setWord(entity.getWord());

        return dictionary;
    }

}
