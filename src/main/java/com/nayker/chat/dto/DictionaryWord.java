package com.nayker.chat.dto;


import com.nayker.chat.entity.DictionaryWordEntity;
import lombok.Data;

@Data
public class DictionaryWord {

    private Long id;
    private String word;

    public static DictionaryWord fromEntity(DictionaryWordEntity entity) {
        DictionaryWord dictionaryWord = new DictionaryWord();
        dictionaryWord.setId(entity.getId());
        dictionaryWord.setWord(entity.getWord());

        return dictionaryWord;
    }

}
