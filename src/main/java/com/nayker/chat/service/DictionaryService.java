package com.nayker.chat.service;


import com.nayker.chat.dto.DictionaryWord;

import java.util.List;

public interface DictionaryService {
    List<DictionaryWord> getDictionary();

    DictionaryWord addDictionaryWord(String word);

    DictionaryWord updateDictionaryWord(long id, String word);

    void deleteDictionaryWord(long id);

    void deleteAll();
}
